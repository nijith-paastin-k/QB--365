package com.students.qb365.ui.subject_mode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.students.qb365.StudyMaterialActivity
import com.students.qb365.databinding.ActivitySubjectModeBinding
import com.students.qb365.ui.subject_mode.testReport.TestReportActivity

class SubjectModeActivity : AppCompatActivity() {

    private var binding: ActivitySubjectModeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectModeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val subID = intent.getStringExtra("sub_id")
        val subName = intent.getStringExtra("sub_name")
        val boardId = intent.getStringExtra("boardId")


        binding?.apply {

            ivBack.setOnClickListener { onBackPressed() }

            ivHeading.text = subName

            clPractice.setOnClickListener {
                val intent = Intent(this@SubjectModeActivity,
                    SubjectChaptersActivity::class.java)
                intent.putExtra("sub_id", subID)
                intent.putExtra("sub_name", subName)
                intent.putExtra("boardId", boardId)
                startActivity(intent)
            }

            clWrite.setOnClickListener {
                val intent = Intent(this@SubjectModeActivity,
                    WriteTestActivity::class.java)
                intent.putExtra("sub_id", subID)
                intent.putExtra("sub_name", subName)
                intent.putExtra("boardId", boardId)
                startActivity(intent)
            }

            ivBack.setOnClickListener {
                onBackPressed()
            }

            clTest.setOnClickListener {
                val intent = Intent(this@SubjectModeActivity,
                    TestReportActivity::class.java)
                intent.putExtra("sub_id", subID)
                intent.putExtra("sub_name", subName)
                intent.putExtra("boardId", boardId)
                startActivity(intent)
            }
            clStudy.setOnClickListener {
                val intent = Intent(this@SubjectModeActivity,
                    StudyMaterialActivity::class.java)
                startActivity(intent)
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}