package com.students.qb365.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityForgotPassBinding
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.successToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForgotPassActivity : AppCompatActivity() {

    private var binding: ActivityForgotPassBinding? = null

    private val model: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            btnSubmit.setOnClickListener {

//                ivBack.setOnClickListener { onBackPressed() }

                val num = etPhone.text.toString().trim()

                if (num.isEmpty()) {
                    errorToast("Enter Phone Number")
                    return@setOnClickListener
                }

                DialogUtil.showProgressDialog(this@ForgotPassActivity)
                model.forgotPassword(num, { forgotPassword ->
                    DialogUtil.dismissProgressDialog()
                    if (forgotPassword.status) {
                        successToast("OTP sent successfully")
                        val intent =
                            Intent(this@ForgotPassActivity, OTPVerificationActivity::class.java)
                        intent.putExtra("otp", forgotPassword.otp.toString())
                        intent.putExtra("phone", num)
                        startActivity(intent)
                    } else {
                        errorToast(forgotPassword.message)
                    }

                }, { s ->
                    errorToast("Phone number is not registered")
                    DialogUtil.dismissProgressDialog()

                })


            }


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}