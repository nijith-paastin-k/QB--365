package com.students.qb365.ui.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.databinding.ActivityChaptersReportBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.report.adapter.ChaptersAdapter
import com.students.qb365.ui.report.model.ChapterReportData
import com.students.qb365.ui.report.viewmodel.ReportViewModel
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapterListReportActivity : AppCompatActivity(), OnClickListener<ChapterReportData> {

    private var binding: ActivityChaptersReportBinding? = null

    private val model : ReportViewModel by viewModels()

    private var subId = ""
    private var subName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChaptersReportBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        subId = intent.getStringExtra("subId")!!
        subName = intent.getStringExtra("subName")!!

        binding?.apply {

            tvHeader.text = subName

            DialogUtil.showProgressDialog(this@ChapterListReportActivity)

            val boardId =
                SharedPrefs.getString(this@ChapterListReportActivity, SharedPrefs.BOARD_ID)

            rvChapters.layoutManager = LinearLayoutManager(this@ChapterListReportActivity)

            model.chaptersReport(boardId!!,subId,{ chapterReport ->
                DialogUtil.dismissProgressDialog()

                val adapter = ChaptersAdapter(chapterReport.data,this@ChapterListReportActivity)
                rvChapters.adapter = adapter

            }, { s ->
                DialogUtil.dismissProgressDialog()
            })

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(t: ChapterReportData, position: Int) {
        val intent = Intent(this,ChapterReportActivity::class.java)
        intent.putExtra("chapId",t.qbsChapterId.toString())
        startActivity(intent)

    }
}