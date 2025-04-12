package com.students.qb365.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityLoginBinding
import com.students.qb365.ui.auth.register.SignUpActivity
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import com.students.qb365.ui.dashboard.MainActivity
import com.students.qb365.utils.*
import com.students.qb365.utils.SharedPrefs.ACCESS_TOKEN
import com.students.qb365.utils.SharedPrefs.BOARD_ID
import com.students.qb365.utils.SharedPrefs.USER_ID
import com.students.qb365.utils.SharedPrefs.USER_NAME
import dagger.hilt.android.AndroidEntryPoint

//Paid status:
//1: paid
//0: unpaid
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null

    private val model: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.apply {
            tvForgotPassword.setOnClickListener {
                openActivity(ForgotPassActivity::class.java)
            }

            ivOldEye.setOnClickListener {
                Utils.eyeToggle(this@LoginActivity, etPassword, ivOldEye)
            }

            btnLogin.setOnClickListener {
                if (etEmail.text.toString().isEmpty()) {
                    errorToast("Enter Email address")
                    return@setOnClickListener
                }

                if (etPassword.text.toString().isEmpty()) {
                    errorToast("Enter Password")
                    return@setOnClickListener
                }

                DialogUtil.showProgressDialog(this@LoginActivity)
                model.doLogin(etEmail.text.toString(), etPassword.text.toString(), { login ->
                    DialogUtil.dismissProgressDialog()
                    if (login.status!!) {
                        SharedPrefs.setString(this@LoginActivity, ACCESS_TOKEN, login.token)
                        SharedPrefs.setString(this@LoginActivity, USER_ID, login.data?.studId)
                        SharedPrefs.setString(this@LoginActivity, USER_NAME, login.data?.studName)
                        SharedPrefs.setString(this@LoginActivity, BOARD_ID, login.board)

                        successToast("Logged In Successfully.")
                        openActivity(MainActivity::class.java)
                    } else {
                        errorToast(login.message!!)
                    }
                }, { s ->
                    DialogUtil.dismissProgressDialog()
                    errorToast("Something went wrong, try again")
                    Log.e("Error", s)
                })
            }


            tvRegister.setOnClickListener {
                infoToast("Please enter phone number first")
                openActivity(CheckPhoneActivity::class.java)
                finishAffinity()
            }


        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}