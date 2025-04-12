package com.students.qb365.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.students.qb365.utils.DialogUtil
import com.students.qb365.R
import com.students.qb365.databinding.ActivityCheckPhoneBinding
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckPhoneActivity : AppCompatActivity() {

    private var binding: ActivityCheckPhoneBinding? = null

    private val model: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckPhoneBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.apply {

//            ivBack.setOnClickListener { onBackPressed() }

            btnSubmit.setOnClickListener {
                if (etPhone.text.toString().isEmpty()) {
                    errorToast("Enter Phone number")
                    return@setOnClickListener
                }

                DialogUtil.showProgressDialog(this@CheckPhoneActivity)
                model.checkPhone(etPhone.text.toString(), { forgotPassword ->
                    DialogUtil.dismissProgressDialog()

                    if (forgotPassword.status) {

                        val intent =
                            Intent(this@CheckPhoneActivity, OTPVerificationActivity::class.java)
                        intent.putExtra("otp", forgotPassword.otp.toString())
                        intent.putExtra("phone", etPhone.text.toString())
                        intent.putExtra("type", "checkPhone")
                        startActivity(intent)

                    } else {
                        errorToast(forgotPassword.message)
                    }

                }, { s ->
                    DialogUtil.dismissProgressDialog()
                    errorToast(getString(R.string.something_went_wrong))

                })

            }

            tvRegister.setOnClickListener {
                openActivity(LoginActivity::class.java)
            }

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}