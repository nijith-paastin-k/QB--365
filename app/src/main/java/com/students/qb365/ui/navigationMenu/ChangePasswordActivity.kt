package com.students.qb365.ui.navigationMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityChangePasswordBinding
import com.students.qb365.ui.dashboard.MainViewModel
import com.students.qb365.utils.SharedPrefs
import com.students.qb365.utils.Utils
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.successToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordActivity : AppCompatActivity() {

    private var binding: ActivityChangePasswordBinding? = null

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {



            ivOldEye.setOnClickListener {
                Utils.eyeToggle(this@ChangePasswordActivity, etName, ivOldEye)
            }

            ivNewEye.setOnClickListener {
                Utils.eyeToggle(this@ChangePasswordActivity, etNewPass, ivNewEye)
            }

            ivConEye.setOnClickListener {
                Utils.eyeToggle(this@ChangePasswordActivity, etConPass, ivConEye)
            }

            btnChangePassword.setOnClickListener {
                val oldPass = etName.text.toString()
                val newPass = etNewPass.text.toString()
                val rePass = etConPass.text.toString()
                if (oldPass.isEmpty()) {
                    errorToast("Enter Old Password")
                    return@setOnClickListener
                }

                if (newPass.isEmpty()) {
                    errorToast("Enter New Password")
                    return@setOnClickListener
                }

                if (rePass.isEmpty()) {
                    errorToast("Enter Confirm Password")
                    return@setOnClickListener
                }

                if (newPass != rePass) {
                    errorToast("New & Confirm password doesn't match")
                    return@setOnClickListener
                }

                val boardId = SharedPrefs.getString(
                    this@ChangePasswordActivity,
                    SharedPrefs.BOARD_ID
                )

                DialogUtil.showProgressDialog(this@ChangePasswordActivity)
                model.changePassword(oldPass, newPass, rePass, boardId!!,{ editProfile ->
                    DialogUtil.dismissProgressDialog()
                    successToast(editProfile.message!!)
                    finish()

                },{ s ->
                    DialogUtil.dismissProgressDialog()

                })

            }

            ivBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}