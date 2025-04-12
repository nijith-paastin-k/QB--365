package com.students.qb365.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.students.qb365.R
import com.students.qb365.databinding.ActivityOtpverificationBinding
import com.students.qb365.ui.auth.register.SignUpActivity
import com.students.qb365.utils.Utils
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.openActivity
import com.students.qb365.utils.successToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OTPVerificationActivity : AppCompatActivity() {

    private var binding: ActivityOtpverificationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            otpView.requestFocus()
            otpView.showSoftInputOnFocus = true

            val otp = intent.getStringExtra("otp")
            var type = intent.getStringExtra("type")
            val phone = intent.getStringExtra("phone")
            if (type == null) type = ""

            btnSubmit.setOnClickListener {
                if (otpView.text.toString().isEmpty()) {
                    errorToast("Enter OTP")
                    return@setOnClickListener
                }

                if (otpView.text.toString() == otp) {
                    Utils.phoneNumber = phone!!
                    successToast("Correct")
                    if (type == "checkPhone") {
                        openActivity(SignUpActivity::class.java)
                        finishAffinity()
                    } else {
                        val intent = Intent(
                            this@OTPVerificationActivity,
                            ResetPasswordActivity::class.java
                        )
                        intent.putExtra("phone", phone)
                        startActivity(intent)
//                        openActivity(ResetPasswordActivity::class.java)
                    }

                } else {
                    errorToast("Wrong OTP, try again")
                }

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}