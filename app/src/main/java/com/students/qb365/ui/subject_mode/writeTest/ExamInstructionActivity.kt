package com.students.qb365.ui.subject_mode.writeTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityExamInstructionBinding
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExamInstructionActivity : AppCompatActivity() {

    private var binding: ActivityExamInstructionBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var testId = ""
    private var boardId = ""
    private var testName = ""
    private var totalMin = ""
    private var totalQue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamInstructionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        testId = intent.getStringExtra("testId")!!
        boardId = intent.getStringExtra("boardId")!!
        testName = intent.getStringExtra("testName")!!

        binding?.apply {

            tvHeader.text = testName

            DialogUtil.showProgressDialog(this@ExamInstructionActivity)

            model.getExamInstruction(testId, boardId, { examInstruction ->
                DialogUtil.dismissProgressDialog()

                totalMin = examInstruction.data?.testDuration!!
                totalQue = examInstruction.data?.testTotalQuestions!!
                tvMin.text = examInstruction.data?.testDuration
                tvQuestion.text = examInstruction.data?.testTotalQuestions

            }, { s ->
                DialogUtil.dismissProgressDialog()


            })

            ivBack.setOnClickListener {
                onBackPressed()
            }

            btnContinue.setOnClickListener {
                val intent = Intent(this@ExamInstructionActivity, TestActivity::class.java)
                intent.putExtra("testId", testId)
                intent.putExtra("boardId", boardId)
                intent.putExtra("duration", totalMin)
                intent.putExtra("totalQuestion", totalQue)
                startActivity(intent)

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}