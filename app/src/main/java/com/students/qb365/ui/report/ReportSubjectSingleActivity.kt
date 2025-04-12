package com.students.qb365.ui.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.databinding.ActivityReportSubjectSingleBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.report.adapter.ReportSingleAdapter
import com.students.qb365.ui.report.model.TestTotal
import com.students.qb365.ui.report.viewmodel.ReportViewModel
import com.students.qb365.ui.subject_mode.ChapterListTestActivity
import com.students.qb365.ui.subject_mode.testReport.SubjectBasedTestReportActivity
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportSubjectSingleActivity : AppCompatActivity(), OnClickListener<TestTotal> {

    private var binding: ActivityReportSubjectSingleBinding? = null

    private val model: ReportViewModel by viewModels()

    private var subId = ""
    private var subName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportSubjectSingleBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        subId = intent.getStringExtra("subId")!!
        subName = intent.getStringExtra("subName")!!

        binding?.apply {

            tvHeader.text = subName

            DialogUtil.showProgressDialog(this@ReportSubjectSingleActivity)

            val board = SharedPrefs.getString(
                this@ReportSubjectSingleActivity,
                SharedPrefs.BOARD_ID
            )
            rvSubjects.layoutManager = LinearLayoutManager(this@ReportSubjectSingleActivity)

            model.getReportSubjectSingle(board!!, subId, { reportSubjectSingle ->
                DialogUtil.dismissProgressDialog()

                val adapter = ReportSingleAdapter(
                    reportSubjectSingle.data?.testTotal!!,
                    this@ReportSubjectSingleActivity
                )
                rvSubjects.adapter = adapter


            }, { s ->
                DialogUtil.dismissProgressDialog()

            })


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(t: TestTotal, position: Int) {
        if (t.title == "Subject Based Test Reports") {
            val intent = Intent(this, SubjectBasedTestReportActivity::class.java)
            intent.putExtra("sub_id", subId)
            startActivity(intent)
        } else {
            val intent = Intent(this,
                ChapterListTestActivity::class.java)
            intent.putExtra("sub_id", subId)
            intent.putExtra("type", "test_report")
            startActivity(intent)
        }
    }
}