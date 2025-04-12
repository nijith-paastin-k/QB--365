package com.students.qb365.ui.subject_mode.writeTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.students.qb365.databinding.ActivityCongratulationBinding
import com.students.qb365.databinding.ActivityContactBinding
import com.students.qb365.ui.subject_mode.writeTest.model.Qst

class CongratulationActivity : AppCompatActivity() {

    private var binding: ActivityCongratulationBinding? = null
    private var boardId = ""
    private var testId = ""
    private var timingSec = ""
    private var qstList = arrayListOf<Qst>()
    private var attempt = ""
    private var skipped = ""
    private var notAttempt = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCongratulationBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        testId = intent.getStringExtra("testId")!!
        boardId = intent.getStringExtra("boardId")!!
        timingSec = intent.getStringExtra("timing")!!
        qstList = intent.getParcelableArrayListExtra("data")!!
        attempt = intent.getStringExtra("attempted")!!
        skipped = intent.getStringExtra("skipped")!!
        notAttempt = intent.getStringExtra("not_attempted")!!

        binding?.apply {

            Handler(Looper.myLooper()!!).postDelayed({
                val intent = Intent(
                    this@CongratulationActivity,
                    FinishTestActivity::class.java
                )
                intent.putExtra("boardId", boardId)
                intent.putExtra("testId", testId)
                intent.putExtra("attempted", attempt.toString())
                intent.putExtra("skipped", skipped.toString())
                intent.putExtra("not_attempted", notAttempt.toString())
                intent.putExtra("timing", timingSec.toString())
                intent.putExtra("data", qstList)
                startActivity(intent)
                finish()

            }, 1800)

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}