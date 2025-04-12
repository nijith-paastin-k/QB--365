package com.students.qb365.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.students.qb365.R
import com.students.qb365.databinding.ActivityOnBoardingBinding
import com.students.qb365.ui.auth.CheckPhoneActivity
import com.students.qb365.ui.auth.LoginActivity
import com.students.qb365.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {


    private var binding: ActivityOnBoardingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        var count = 0

        binding?.apply {

            ivNext.setOnClickListener {
                if (count == 0) {
                    count++

                    Glide.with(this@OnBoardingActivity)
                        .load(R.drawable.ic_onboard2)
                        .into(ivImage)
                    tvTitle.text = "Test"
                    tvDesc.text =
                        "Attend Quality Questions based on latest exam pattern and take the exclusive test paper's designed by Experience faculty."

                    Glide.with(this@OnBoardingActivity)
                        .load(R.drawable.ic_next2)
                        .into(ivNext)

                } else if (count == 1) {
                    count++

                    Glide.with(this@OnBoardingActivity)
                        .load(R.drawable.ic_onboard3)
                        .into(ivImage)
                    tvTitle.text = "Analysis"
                    tvDesc.text =
                        "QB365 has a powerful learning & testing platform backed Performance analysis and generation of extensive reports."

                    Glide.with(this@OnBoardingActivity)
                        .load(R.drawable.ic_next3)
                        .into(ivNext)

                } else if (count == 2) {
                    openActivity(CheckPhoneActivity::class.java)
                    finish()
                }
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}