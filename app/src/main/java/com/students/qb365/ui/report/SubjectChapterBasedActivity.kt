package com.students.qb365.ui.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.databinding.ActivitySubjectChapterBasedBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.report.adapter.SubjectChapterBasedAdapter
import com.students.qb365.ui.report.model.SubjectChapterBasedData
import com.students.qb365.ui.report.viewmodel.ReportViewModel
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectChapterBasedActivity : AppCompatActivity(), OnClickListener<SubjectChapterBasedData> {

    private var binding: ActivitySubjectChapterBasedBinding? = null

    private val model: ReportViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectChapterBasedBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            rvSubjects.layoutManager = LinearLayoutManager(this@SubjectChapterBasedActivity)

            DialogUtil.showProgressDialog(this@SubjectChapterBasedActivity)
            val boardId =
                SharedPrefs.getString(this@SubjectChapterBasedActivity, SharedPrefs.BOARD_ID)

            model.subjectChapterBased(boardId!!, { subjectChapterBased ->
                DialogUtil.dismissProgressDialog()

                val adapter = SubjectChapterBasedAdapter(
                    subjectChapterBased.data,
                    this@SubjectChapterBasedActivity
                )
                rvSubjects.adapter = adapter

            }, { s ->

            })


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(t: SubjectChapterBasedData, position: Int) {
        val intent = Intent(this, ChapterListReportActivity::class.java)
        intent.putExtra("subId", t.qbsSubId.toString())
        intent.putExtra("subName", t.name)
        startActivity(intent)
    }
}