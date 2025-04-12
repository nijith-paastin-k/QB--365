package com.students.qb365.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.PaymentSheetResultCallback
import com.students.qb365.api.ApiService
import com.students.qb365.databinding.ActivityPaymentBinding
import com.students.qb365.ui.auth.LoginActivity
import com.students.qb365.ui.auth.model.Customers
import com.students.qb365.ui.auth.model.EphericalKey
import com.students.qb365.ui.auth.model.PaymentIntent
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import com.students.qb365.utils.*
import com.students.qb365.utils.Constants.isFromRegister
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


@AndroidEntryPoint
class PaymentActivity : AppCompatActivity() {


    private var binding: ActivityPaymentBinding? = null

    private val model: AuthViewModel by viewModels()

    private var packID = ""
    private var boardId = ""
    private var typeID = ""
    private var subId = ""

    private var subList = ArrayList<String>()
    private var chaptersList = ArrayList<String>()
    private lateinit var apiService: ApiService

    private val SECRET_KEY =
//        "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" //live

        "sk_test_51LyFRKSJrdU8OkR6Kkpe5jnQbW6QjnqGQ2AnwSlv6SR0ICJeZjyiwhb6Wa1EVRlVVIWxKqSq51vCwHndN7OPIKgc009ZqDZbkq" //test
    private val PUBLIC_KEY =
//        "pk_live_51Lp3sbSHGNiA8qdYufIFx1tQVHVJFWiKg9V66Zt4lIvsqZCNtcoVRfLJimxflgSHlIHs7J4LEd7Eb7qkXsuheTN800t07hb4VZ" //live
        "pk_test_51LyFRKSJrdU8OkR6K1uAv1r3JDxUslVIhM3mghgViFlHf9XkvHftxFOPmrKfDMsvgqvdKDUlRknVFPyhQs0JR9Yn00kblOD18U" //test

    private var customerId = ""
    private var ephericalKey = ""
    private var clientSecret = ""
    private var amount = ""

    private lateinit var paymentSheet: PaymentSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        apiService = APIClient.client?.create(ApiService::class.java)!!

        PaymentConfiguration.init(this, PUBLIC_KEY)



        paymentSheet = PaymentSheet(
            this
        ) { paymentSheetResult ->
            onPaymentResult(paymentSheetResult)
        }


        isFromRegister = SharedPrefs.getString(this, SharedPrefs.ACCESS_TOKEN)?.isEmpty()!!


//        try {
//            isFromRegister = intent.getBooleanExtra("type", false)
//        } catch (e: Exception) {
//        }


        try {
            packID = intent.getStringExtra("packID")!!
            boardId = intent.getStringExtra("boardId")!!
            typeID = intent.getStringExtra("typeID")!!
        } catch (e: Exception) {
        }

        subId = try {
            intent.getStringExtra("subjectId")!!
        } catch (e: Exception) {
            ""
        }

        subList = try {
            intent.getStringArrayListExtra("subjectList")!!
        } catch (e: Exception) {
            arrayListOf()
        }

        chaptersList = try {
            intent.getStringArrayListExtra("chapterList")!!
        } catch (e: Exception) {
            arrayListOf()
        }

        binding?.apply {

//            if (Constants.IS_CART.isNotEmpty()) {
            if (isFromRegister) {
                signUp()
            } else {
                btnContinue.gone()
            }
//            }

            btnContinue.setOnClickListener {
                val token = "Bearer " + SharedPrefs.getString(this@PaymentActivity, SharedPrefs.ACCESS_TOKEN)
                model.paymentSuccess(token, { payment ->
                    if (payment.status!!) {
                        if (isFromRegister) {
                            successToast("Registered Successfully, Login now")
                            openActivity(LoginActivity::class.java)
                            finishAffinity()
                        }
                    } else {
                        successToast("Purchased successfully")
                        finish()

                    }
                }, { s ->
                    Log.e("ERROR", s)
                })
            }

            btnBack.setOnClickListener {
                onBackPressed()
            }

            cardBankTransfer.setOnClickListener {
                openActivity(BankTransferActivity::class.java)
            }

            cardGooglePay.setOnClickListener {
                openActivity(QrCodeActivity::class.java)
            }

            btnBuyPlan1.setOnClickListener {
                amount = "1250" + "00"
                stripeInit()
            }

            btnBuyPlan3.setOnClickListener {
                amount = "2750" + "00"
                stripeInit()
            }

            btnBuyPlan2.setOnClickListener {
                amount = "1750" + "00"
                stripeInit()
            }

        }

    }


    private fun onPaymentResult(paymentSheetResult: PaymentSheetResult) {
        Log.e("STRIPE", paymentSheetResult.toString())
        if (paymentSheetResult is PaymentSheetResult.Completed) {
            val token = "Bearer " + SharedPrefs.getString(this, SharedPrefs.ACCESS_TOKEN)
            model.paymentSuccess(token, { payment ->
                if (payment.status!!) {
                    if (isFromRegister) {
                        successToast("Registered Successfully, Login now")
                        openActivity(LoginActivity::class.java)
                        finishAffinity()
                    }
                } else {
                    successToast("Purchased successfully")
                    finish()

                }
            }, { s ->
                Log.e("ERROR", s)
            })
        }
    }

    private fun getEphericalKey(id: String) {
        val call: Call<EphericalKey> =
            apiService.ephemeralKeys("Bearer $SECRET_KEY", "2022-08-01", id)
        call.enqueue(object : Callback<EphericalKey?> {
            override fun onResponse(call: Call<EphericalKey?>, response: Response<EphericalKey?>) {
                ephericalKey = response.body()?.id!!
                Log.e("ephericalKey", ephericalKey)
                paymentIntent()
            }

            override fun onFailure(call: Call<EphericalKey?>, t: Throwable) {

            }
        })
    }


    private fun paymentIntent() {
        val call: Call<PaymentIntent> =
            apiService.paymentIntent("Bearer $SECRET_KEY", customerId, amount, "inr", "true")
        call.enqueue(object : Callback<PaymentIntent?> {
            override fun onResponse(
                call: Call<PaymentIntent?>,
                response: Response<PaymentIntent?>
            ) {
                clientSecret = response.body()?.clientSecret!!
                Log.e("clientSecret", clientSecret)

                paymentFlow()
            }

            override fun onFailure(call: Call<PaymentIntent?>, t: Throwable) {

            }
        })
    }

    private fun paymentFlow() {
        paymentSheet.presentWithPaymentIntent(
            clientSecret,
            PaymentSheet.Configuration(
                "QB365",
                PaymentSheet.CustomerConfiguration(customerId, ephericalKey),
                PaymentSheet.GooglePayConfiguration(
                    PaymentSheet.GooglePayConfiguration.Environment.Test,
                    "+91",
                    "INR"
                )
            )
        )
    }


    private fun stripeInit() {
        val call: Call<Customers> = apiService.createCustomers("Bearer $SECRET_KEY")
        call.enqueue(object : Callback<Customers?> {
            override fun onResponse(call: Call<Customers?>, response: Response<Customers?>) {
                customerId = response.body()?.id!!
                Log.e("CUSTOMER_ID", customerId)
                getEphericalKey(customerId)
            }

            override fun onFailure(call: Call<Customers?>, t: Throwable) {

            }
        })

    }

    private fun ccAvenue(userId: Int) {
        val amount = "10"
        val vAccessCode = "AVQX29JI63CG32XQGC"
        val vMerchantId: String = "6659"
        val vCurrency: String = "INR"

        //generating new order number for every transaction
        val randomNum: Int = randInt(0, 9999999)
        val order_ID = "APP$userId"

        val intent = Intent(this@PaymentActivity, WebViewActivity::class.java)
        intent.putExtra(
            AvenuesParams.ACCESS_CODE,
            vAccessCode
        )
        intent.putExtra(
            AvenuesParams.MERCHANT_ID,
            vMerchantId
        )
        intent.putExtra(
            AvenuesParams.ORDER_ID,
            order_ID
        )
        intent.putExtra(
            AvenuesParams.CURRENCY,
            vCurrency
        )
        intent.putExtra(
            AvenuesParams.AMOUNT,
            amount
        )

        intent.putExtra(
            AvenuesParams.REDIRECT_URL,
            AvenuesParams.redirectUrl
        )
        intent.putExtra(
            AvenuesParams.CANCEL_URL,
            AvenuesParams.cancelUrl
        )
        intent.putExtra(
            AvenuesParams.RSA_KEY_URL,
            AvenuesParams.rsaKeyUrl
        )

        startActivity(intent)
    }

    private fun signUp() {
        val map = HashMap<String, Any>()

        val signUpData = Utils.signUpData

        map["name"] = signUpData.name!!
        map["email"] = signUpData.email!!
        map["phone"] = Utils.phoneNumber
        map["password"] = signUpData.password!!
        map["referal"] =
            if (signUpData.refCodeStd!! == null) "" else signUpData.refCodeStd!!
        map["sreferal"] =
            if (signUpData.refCodeStaff!! == null) "" else signUpData.refCodeStaff!!
        map["school"] = signUpData.schoolName!!
        map["address"] = signUpData.address!!
        map["state"] = signUpData.stateID!!
        map["city"] = signUpData.cityId!!
        map["type"] = typeID
        map["pack_id"] = "14"
        map["board"] = boardId


        val studentMap = HashMap<String, Any>()
        studentMap[boardId] = packID

        map["standard"] = studentMap

        if (subList.size > 0) {
            val subjectsMap = HashMap<String, Any>()

            for (i in subList.indices) {
                subjectsMap[i.toString()] = subList[i]
            }

            map["subjects"] = subjectsMap
        }

        if (chaptersList.size > 0) {
            val chaptersMap = HashMap<String, Any>()
            val chap = HashMap<String, Any>()

            for (i in chaptersList.indices) {
                chap[i.toString()] = chaptersList[i]
            }
            chaptersMap[subId] = chap
            map["subjects"] = chaptersMap
        }


        DialogUtil.showProgressDialog(this@PaymentActivity)
        model.register(map, { register ->
            DialogUtil.dismissProgressDialog()

            if (register.status!!) {
                SharedPrefs.setString(this, SharedPrefs.ACCESS_TOKEN, register.token)

            } else {
                errorToast(register.message!!)
            }


        }, { s ->
            DialogUtil.dismissProgressDialog()
        })
    }


    fun randInt(min: Int, max: Int): Int {
        val rand = Random()
        return rand.nextInt(max - min + 1) + min
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}


internal object APIClient {
    private var retrofit: Retrofit? = null
    val client: Retrofit?
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.stripe.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit
        }
}