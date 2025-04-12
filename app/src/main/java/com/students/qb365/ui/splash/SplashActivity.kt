package com.students.qb365.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.students.qb365.R
import com.students.qb365.ui.auth.LoginActivity
import com.students.qb365.ui.auth.register.PaymentActivity
import com.students.qb365.ui.dashboard.MainActivity
import com.students.qb365.ui.onboarding.OnBoardingActivity
import com.students.qb365.utils.SharedPrefs


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler(Looper.myLooper()!!).postDelayed({
            if (SharedPrefs.getString(this, SharedPrefs.ACCESS_TOKEN)?.isEmpty()!!) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            finish()

        }, 2500)

    }
}