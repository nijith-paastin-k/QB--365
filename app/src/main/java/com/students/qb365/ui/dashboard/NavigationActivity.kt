package com.students.qb365.ui.dashboard

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.students.qb365.R
import com.students.qb365.databinding.ActivityNavigationBinding
import com.students.qb365.ui.auth.CheckPhoneActivity
import com.students.qb365.ui.auth.model.Payment
import com.students.qb365.ui.auth.register.BoardActivity
import com.students.qb365.ui.auth.register.PaymentActivity
import com.students.qb365.ui.dashboard.model.Profile
import com.students.qb365.ui.navigationMenu.*
import com.students.qb365.ui.report.ReportTypeListActivity
import com.students.qb365.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    private var binding: ActivityNavigationBinding? = null

    private val model: MainViewModel by viewModels()

    private lateinit var profile: Profile

    private var boardId = ""

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                getData()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            ivClose.setOnClickListener {
                finish()
            }

            tvEditProfile.setOnClickListener {
                val intent = Intent(this@NavigationActivity, EditProfileActivity::class.java)
                intent.putExtra("data", profile)
                resultLauncher.launch(intent)
            }

            DialogUtil.showProgressDialog(this@NavigationActivity)

            boardId = SharedPrefs.getString(this@NavigationActivity, SharedPrefs.BOARD_ID)!!

            getData()

            tvLogout.setOnClickListener {
                AlertDialog.Builder(this@NavigationActivity)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            SharedPrefs.clearPrefs(this@NavigationActivity)
                            openActivity(CheckPhoneActivity::class.java)
                            finishAffinity()
                        }
                    })
                    .setNegativeButton("No", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {

                        }
                    })
                    .show()

            }

            tvChangePass.setOnClickListener {
                openActivity(ChangePasswordActivity::class.java)
            }

            tvWriteTest.setOnClickListener {
                openActivity(TestSubjectsActivity::class.java)
            }

            tvDashboard.setOnClickListener {
                onBackPressed()
            }

            tvPractice.setOnClickListener {
                val intent = Intent(
                    this@NavigationActivity,
                    TestSubjectsActivity::class.java
                )
                intent.putExtra("type", "practise")
                startActivity(intent)
            }

            tvContactUs.setOnClickListener {
                openActivity(ContactActivity::class.java)
            }

            tvInvite.setOnClickListener {
                openActivity(InviteEarnActivity::class.java)
            }

            tvForYou.setOnClickListener {
                openActivity(ForYouActivity::class.java)
            }

            tvMyOrder.setOnClickListener {
                openActivity(MyOrderActivity::class.java)
            }

            tvReport.setOnClickListener {
                openActivity(ReportTypeListActivity::class.java)
            }


            tvUpgrade.setOnClickListener {
                Constants.isFromRegister = false
                val i = Intent(this@NavigationActivity, BoardActivity::class.java)
                startActivity(i)
            }


            tvPrivacyPolicy.setOnClickListener {
                Utils.openUrl(this@NavigationActivity, "https://www.qb365.in/privacy_policy")
            }

            tvTermsConditions.setOnClickListener {
                Utils.openUrl(this@NavigationActivity, "https://www.qb365.in/terms_and_condition")
            }


        }

    }

    private fun getData() {
        binding?.apply {
            model.profile(boardId, { profile ->
                DialogUtil.dismissProgressDialog()

                this@NavigationActivity.profile = profile

                ivProfile.loadCircular(profile.data?.image!!, R.drawable.ic_user_placeholder)
                tvUserName.text = profile.data?.studName
                tvStd.text = profile.department

                model.validity({ validity ->
                    tvValidity.text = validity.id
                }, { s ->
                    Log.e("Error", s)
                })

            }, { s ->
                DialogUtil.dismissProgressDialog()

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onBackPressed() {

        overridePendingTransition(
            android.R.anim.slide_out_right,
            android.R.anim.slide_in_left
        )
        super.onBackPressed()
    }
}