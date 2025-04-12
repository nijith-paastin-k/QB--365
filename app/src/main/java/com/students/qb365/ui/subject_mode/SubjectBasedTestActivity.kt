package com.students.qb365.ui.subject_mode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivitySubjectBasedTestBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.subject_mode.adapter.SubjectBasedTestAdapter
import com.students.qb365.ui.subject_mode.model.Cps
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.ui.subject_mode.writeTest.ExamInstructionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectBasedTestActivity : AppCompatActivity(), OnClickListener<Cps> {

    private var binding: ActivitySubjectBasedTestBinding? = null
    private val model: SubjectDemoViewModel by viewModels()
    private var sub_id = ""
    private var boardId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectBasedTestBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        sub_id = intent.getStringExtra("sub_id")!!
        boardId = intent.getStringExtra("boardId")!!

        binding?.apply {

            rvSubjectBased.layoutManager = LinearLayoutManager(this@SubjectBasedTestActivity)

            DialogUtil.showProgressDialog(this@SubjectBasedTestActivity)
            model.getSubjectBasedTest(sub_id, boardId, { subjectBasedTest ->
                DialogUtil.dismissProgressDialog()

                chapCount.text = subjectBasedTest.data?.cps!!.size.toString() + " Tests"

                val subjectBasedTestAdapter =
                    SubjectBasedTestAdapter(
                        subjectBasedTest.data?.cps!!,
                        this@SubjectBasedTestActivity
                    )
                rvSubjectBased.adapter = subjectBasedTestAdapter

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

    override fun onClick(t: Cps, position: Int) {
        val intent = Intent(this, ExamInstructionActivity::class.java)
        intent.putExtra("testId", t.testId)
        intent.putExtra("boardId", boardId)
        intent.putExtra("testName", t.testName)
        startActivity(intent)
    }
}