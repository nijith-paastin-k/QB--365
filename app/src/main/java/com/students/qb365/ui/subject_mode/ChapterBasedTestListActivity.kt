package com.students.qb365.ui.subject_mode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityChapterBasedBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.subject_mode.adapter.ChapterBasedTestAdapter
import com.students.qb365.ui.subject_mode.model.ChapterBased
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.ui.subject_mode.writeTest.ExamInstructionActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChapterBasedTestListActivity : AppCompatActivity(), OnClickListener<ChapterBased> {

    private var binding: ActivityChapterBasedBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var testID = ""
    private var boardID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBasedBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        testID = intent.getStringExtra("testId")!!
        boardID = intent.getStringExtra("boardId")!!

        binding?.apply {

            ivBack.setOnClickListener { onBackPressed() }

            rvChapterList.layoutManager = LinearLayoutManager(this@ChapterBasedTestListActivity)

            DialogUtil.showProgressDialog(this@ChapterBasedTestListActivity)

            model.getChapterBasedTest(testID, boardID, { chapterBasesTest ->
                DialogUtil.dismissProgressDialog()

                val adapter = ChapterBasedTestAdapter(
                    chapterBasesTest.data?.chapter!!,
                    this@ChapterBasedTestListActivity
                )

                rvChapterList.adapter = adapter

            }, { s ->
                DialogUtil.dismissProgressDialog()

            })

            ivBack.setOnClickListener { onBackPressed() }


        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(t: ChapterBased, position: Int) {
        val intent = Intent(this, ExamInstructionActivity::class.java)
        intent.putExtra("testId", t.testId)
        intent.putExtra("boardId", boardID)
        intent.putExtra("testName", t.testName)
        startActivity(intent)
    }
}