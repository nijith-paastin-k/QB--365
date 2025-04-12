package com.students.qb365.ui.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.R
import com.students.qb365.databinding.ActivityTestReportBinding
import com.students.qb365.databinding.ActivityTestWiseReportBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.report.adapter.TestBaseSubjectAdapter
import com.students.qb365.ui.report.model.Subject
import com.students.qb365.ui.report.viewmodel.ReportViewModel
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestWiseReportActivity : AppCompatActivity(), OnClickListener<Subject> {

    private var binding: ActivityTestWiseReportBinding? = null

    private val model: ReportViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestWiseReportBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            DialogUtil.showProgressDialog(this@TestWiseReportActivity)

            rvSubjects.layoutManager = LinearLayoutManager(this@TestWiseReportActivity)

            val board = SharedPrefs.getString(
                this@TestWiseReportActivity,
                SharedPrefs.BOARD_ID
            )

            model.getTestWiseReport(board!!, { testWiseReport ->
                DialogUtil.dismissProgressDialog()


                val adapter = TestBaseSubjectAdapter(
                    testWiseReport.data?.subject!!,
                    this@TestWiseReportActivity
                )
                rvSubjects.adapter = adapter

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

    override fun onClick(t: Subject, position: Int) {
        val intent = Intent(this, ReportSubjectSingleActivity::class.java)
        intent.putExtra("subId", t.qbsSubId.toString())
        intent.putExtra("subName", t.qbsSubName)
        startActivity(intent)
    }
}