package com.students.qb365.ui.navigationMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityContactBinding
import com.students.qb365.ui.dashboard.MainViewModel
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.successToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {

    private var binding: ActivityContactBinding? = null

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.apply {

            btnSend.setOnClickListener {
                if (edtMsg.text.toString().isEmpty()) {
                    errorToast("Message is empty")
                    return@setOnClickListener
                }

                DialogUtil.showProgressDialog(this@ContactActivity)
                model.contact(edtMsg.text.toString(),{ baseResponse ->
                    DialogUtil.dismissProgressDialog()

                    successToast(baseResponse.message!!)
                    finish()

                }, { s ->
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