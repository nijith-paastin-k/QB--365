package com.students.qb365.ui.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.students.qb365.R
import com.students.qb365.databinding.ActivityReportTypeListBinding
import com.students.qb365.utils.openActivity


class ReportTypeListActivity : AppCompatActivity() {

    private var binding: ActivityReportTypeListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReportTypeListBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.apply {


            cardOverallReport.setOnClickListener{
                openActivity(OverAllReportActivity::class.java)
            }

            cardTestwiseReport.setOnClickListener{
                openActivity(TestWiseReportActivity::class.java)
            }

            cardSubjectBasedReport.setOnClickListener{
                openActivity(SubjectBasedReportActivity::class.java)
            }

            cardChapterBasedReport.setOnClickListener{
                openActivity(SubjectChapterBasedActivity::class.java)
            }

            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}