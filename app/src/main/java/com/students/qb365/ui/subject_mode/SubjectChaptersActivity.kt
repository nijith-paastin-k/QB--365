package com.students.qb365.ui.subject_mode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivitySubjectChaptersBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.subject_mode.adapter.SubjectsChaptersAdapter
import com.students.qb365.ui.subject_mode.model.Chapters
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectChaptersActivity : AppCompatActivity(), OnClickListener<Chapters> {

    private var binding: ActivitySubjectChaptersBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var subID = ""
    private var subName = ""
    private var boardId = ""
    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectChaptersBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        subID = intent.getStringExtra("sub_id")!!
        subName = intent.getStringExtra("sub_name")!!
        boardId = intent.getStringExtra("boardId")!!
        try {
            type = intent.getStringExtra("type")!!
        } catch (e: Exception) {
            type = ""
        }

        binding?.apply {

            tvHeader.text = subName

            if (type == "test")
                textView3.text = "Please select test"

            rvChapters.layoutManager = LinearLayoutManager(this@SubjectChaptersActivity)

            DialogUtil.showProgressDialog(this@SubjectChaptersActivity)

            model.subjectChapter(subID, boardId, { subjectChapter ->
                DialogUtil.dismissProgressDialog()

                chapCount.text = subjectChapter.data?.chapters!!.size.toString() + " Chapters"

                val subjectsChaptersAdapter = SubjectsChaptersAdapter(
                    subjectChapter.data?.chapters!!,
                    this@SubjectChaptersActivity
                )

                rvChapters.adapter = subjectsChaptersAdapter

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

    override fun onClick(t: Chapters, position: Int) {
        val intent = Intent(this, QuestionTypeActivity::class.java)
        intent.putExtra("subID", subID)
        intent.putExtra("chapterID", t.qbsChapterId)
        intent.putExtra("boardID", boardId)
        intent.putExtra("chapterName", t.qbsChapterName)
        startActivity(intent)
    }
}