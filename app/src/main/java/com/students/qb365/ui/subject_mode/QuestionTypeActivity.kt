package com.students.qb365.ui.subject_mode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityQuestionTypeBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.subject_mode.adapter.QuestionTypeAdapter
import com.students.qb365.ui.subject_mode.model.Qstarr
import com.students.qb365.ui.subject_mode.model.QuestionType
import com.students.qb365.ui.subject_mode.practiceQuestion.PracticeQuestionActivity
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionTypeActivity : AppCompatActivity(), OnClickListener<Qstarr> {

    private var binding: ActivityQuestionTypeBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var subID = ""
    private var chapterID = ""
    private var boardID = ""
    private var chapterName = ""
    private lateinit var list: ArrayList<Qstarr>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionTypeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        boardID = intent.getStringExtra("boardID")!!
        chapterName = intent.getStringExtra("chapterName")!!
        chapterID = intent.getStringExtra("chapterID")!!
        subID = intent.getStringExtra("subID")!!

        binding?.apply  {

            tvHeader.text = chapterName

            rvQuestionType.layoutManager = LinearLayoutManager(this@QuestionTypeActivity)

            DialogUtil.showProgressDialog(this@QuestionTypeActivity)

            model.questionType(subID, boardID, chapterID, { questionType: QuestionType ->
                DialogUtil.dismissProgressDialog()

                list = questionType.data?.qstarr!!

                val adapter =
                    QuestionTypeAdapter(
                        list,
                        this@QuestionTypeActivity
                    )

                rvQuestionType.adapter = adapter

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

    override fun onClick(t: Qstarr, position: Int) {
        val intent = Intent(this, PracticeQuestionActivity::class.java)
        intent.putExtra("subID", subID)
        intent.putExtra("chapterID", chapterID)
        intent.putExtra("boardID", boardID)
        intent.putExtra("quesType", t.qbsQsTypeId)
        intent.putExtra("chapterName", chapterName)
        intent.putExtra("list", list)
        intent.putExtra("position", position)
        startActivity(intent)

    }
}