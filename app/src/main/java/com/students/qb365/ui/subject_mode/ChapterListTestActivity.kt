package com.students.qb365.ui.subject_mode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityChapterListTestBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.subject_mode.adapter.ChapterListAdapter
import com.students.qb365.ui.subject_mode.model.Chapter
import com.students.qb365.ui.subject_mode.testReport.ChapterBasedTestReportActivity
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapterListTestActivity : AppCompatActivity(), OnClickListener<Chapter> {

    private var binding: ActivityChapterListTestBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var sub_id = ""
    private var boardId = ""
    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterListTestBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        sub_id = intent.getStringExtra("sub_id")!!
        type = try {
            intent.getStringExtra("type")!!
        } catch (e: Exception) {
            ""
        }
        boardId = SharedPrefs.getString(this,SharedPrefs.BOARD_ID)!!


        binding?.apply {

            ivBack.setOnClickListener { onBackPressed() }

            rvChapterList.layoutManager = LinearLayoutManager(this@ChapterListTestActivity)

            DialogUtil.showProgressDialog(this@ChapterListTestActivity)
            model.getChapterListTest(sub_id, boardId, { chapterListTest ->
                DialogUtil.dismissProgressDialog()

                chapCount.text = chapterListTest.data?.chapter!!.size.toString() + " Tests"

                val adapter = ChapterListAdapter(
                    chapterListTest.data?.chapter!!,
                    this@ChapterListTestActivity
                )

                rvChapterList.adapter = adapter

            }, { s ->
                DialogUtil.dismissProgressDialog()

            })


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(t: Chapter, position: Int) {
        if (type.isEmpty()) {
            val intent = Intent(this, ChapterBasedTestListActivity::class.java)
            intent.putExtra("testId", t.qbsChapterId)
            intent.putExtra("boardId", boardId)
            startActivity(intent)
        } else {
            val intent = Intent(this, ChapterBasedTestReportActivity::class.java)
            intent.putExtra("chapId", t.qbsChapterId)
            startActivity(intent)
        }

    }
}