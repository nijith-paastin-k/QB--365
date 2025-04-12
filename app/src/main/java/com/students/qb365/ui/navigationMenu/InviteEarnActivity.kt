package com.students.qb365.ui.navigationMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.students.qb365.R
import com.students.qb365.databinding.ActivityInviteEarnBinding
import com.students.qb365.ui.dashboard.MainViewModel
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InviteEarnActivity : AppCompatActivity() {

    private var binding: ActivityInviteEarnBinding? = null

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInviteEarnBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            val user = SharedPrefs.getString(this@InviteEarnActivity, SharedPrefs.USER_NAME)
            tvTopLine.text = "Hi $user, Your Total QB Cash Is"

                DialogUtil.showProgressDialog(this@InviteEarnActivity)
            model.inviteEarn({ inviteEarn ->
                DialogUtil.dismissProgressDialog()

                tvCode.text = inviteEarn.data?.info?.code
                if (inviteEarn.data?.info?.points == "") {
                    tvCash.text = "INR 0"
                } else {
                    tvCash.text = "INR ${inviteEarn.data?.info?.points}"
                }


            }, { s ->
                DialogUtil.dismissProgressDialog()

            })

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