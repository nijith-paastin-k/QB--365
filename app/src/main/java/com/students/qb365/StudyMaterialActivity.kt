package com.students.qb365

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.students.qb365.databinding.ActivityStudyMaterialBinding
import com.students.qb365.databinding.ActivitySubjectModeBinding
import com.students.qb365.ui.subject_mode.SubjectChaptersActivity
import com.students.qb365.ui.subject_mode.WriteTestActivity
import com.students.qb365.ui.subject_mode.testReport.TestReportActivity

class StudyMaterialActivity : AppCompatActivity() {
    private var binding: ActivityStudyMaterialBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyMaterialBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val subName = "I Am Preparing For"


        binding?.apply {

            ivBack.setOnClickListener { onBackPressed() }

            ivHeading.text = subName

            tamilmedium.setOnClickListener {
                val intent = Intent(this@StudyMaterialActivity,
                    StudyMaterialStateBoardActivity::class.java)
//                    StudyMaterialWebviewActivity::class.java)
                startActivity(intent)
            }

            englishmedium.setOnClickListener {
                val intent = Intent(this@StudyMaterialActivity,
                    StudyMaterialCBSEActivity::class.java)
                startActivity(intent)
            }
//            entranceexams.setOnClickListener {
//                val intent = Intent(this@StudyMaterialActivity,
//                    StudyMaterialCBSEActivity::class.java)
//                startActivity(intent)
//            }

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