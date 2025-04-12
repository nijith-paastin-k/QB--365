package com.students.qb365.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityResetPasswordBinding
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordActivity : AppCompatActivity() {

    private var binding: ActivityResetPasswordBinding? = null

    private val model: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val phone = intent.getStringExtra("phone")

        binding?.apply {

            btnSubmit.setOnClickListener {
                val pass = etPassword.text.toString()
                val cPass = etConfirmPassword.text.toString()

                if (pass.isEmpty()) {
                    errorToast("Enter New Password")
                    return@setOnClickListener
                }

                if (cPass.isEmpty()) {
                    errorToast("Enter Confirm Password")
                    return@setOnClickListener
                }


                if (pass != cPass) {
                    errorToast("New & Confirm Password doesn't match")
                    return@setOnClickListener
                }

                DialogUtil.showProgressDialog(this@ResetPasswordActivity)
                model.resetPassword(phone!!, pass, cPass, { resetPassword ->
                    DialogUtil.dismissProgressDialog()

                    if (resetPassword.status!!){
                        openActivity(LoginActivity::class.java)
                        finishAffinity()
                    }

                }, { s ->
                    DialogUtil.dismissProgressDialog()
                })

//                openActivity(SignUpActivity::class.java)
            }


        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}