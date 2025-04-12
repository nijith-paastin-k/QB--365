package com.students.qb365.ui.subject_mode.writeTest

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.students.qb365.utils.DialogUtil
import com.students.qb365.R
import com.students.qb365.databinding.ActivityTestBinding
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.ui.subject_mode.writeTest.model.Qst
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {

    private var binding: ActivityTestBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var testId = ""
    private var boardId = ""
    private var totalMin = ""
    private var totalQue = ""
    private var position = 0
    private var isPrevious = false
    private var timingSec: Long? = null
    private var qstList = arrayListOf<Qst>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        testId = intent.getStringExtra("testId")!!
        boardId = intent.getStringExtra("boardId")!!
        totalMin = intent.getStringExtra("duration")!!
        totalQue = intent.getStringExtra("totalQuestion")!!

        binding?.apply {

            DialogUtil.showProgressDialog(this@TestActivity)

            model.writeTest(testId, boardId, { writeTest ->

                DialogUtil.dismissProgressDialog()
                qstList = writeTest.data?.qst!!

                if (qstList.size > 0) {
                    showData(qstList[position])
                    startTimer()
                } else {
                    errorToast("No Data Found")
                }

            }, { s ->
                DialogUtil.dismissProgressDialog()
                errorToast(getString(R.string.something_went_wrong))
            })

            btnNext.setOnClickListener {
                Log.e("Before", position.toString() + " : " + qstList.size)
                if (position < qstList.size - 1) {
                    isPrevious = false
                    if (checkAttempted()) {
                        qstList[position].attempted = true
                    } else {
                        qstList[position].skipped = true
                    }
                    position++
                    showData(qstList[position])
                    setDisabled()
                    Log.e("Before After", position.toString() + " : " + qstList.size)
                } else {
                    finishTest()
                }
            }

            btnPrevious.setOnClickListener {
                if (position > 0) {
                    isPrevious = true
                    position--
                    showData(qstList[position])
                }

            }

            rlA.setOnClickListener {

                qstList[position].revised = isPrevious
                showEnabled(0)

//                qstList[position].childOption[0].isSelected = true
//                qstList[position].childOption[1].isSelected = false
//                qstList[position].childOption[2].isSelected = false
//                qstList[position].childOption[3].isSelected = false

                rlA.isSelected = true
                rlB.isSelected = false
                rlC.isSelected = false
                rlD.isSelected = false

            }

            rlB.setOnClickListener {

                showEnabled(1)
//                qstList[position].childOption[0].isSelected = false
//                qstList[position].childOption[1].isSelected = true
//                qstList[position].childOption[2].isSelected = false
//                qstList[position].childOption[3].isSelected = false

                qstList[position].revised = isPrevious
                rlA.isSelected = false
                rlB.isSelected = true
                rlC.isSelected = false
                rlD.isSelected = false
            }

            rlC.setOnClickListener {

                showEnabled(2)
//                qstList[position].childOption[0].isSelected = false
//                qstList[position].childOption[1].isSelected = false
//                qstList[position].childOption[2].isSelected = true
//                qstList[position].childOption[3].isSelected = false

                qstList[position].revised = isPrevious
                rlA.isSelected = false
                rlB.isSelected = false
                rlC.isSelected = true
                rlD.isSelected = false
            }

            rlD.setOnClickListener {

                showEnabled(3)
//                qstList[position].childOption[0].isSelected = false
//                qstList[position].childOption[1].isSelected = false
//                qstList[position].childOption[2].isSelected = false
//                qstList[position].childOption[3].isSelected = true

                qstList[position].revised = isPrevious
                rlA.isSelected = false
                rlB.isSelected = false
                rlC.isSelected = false
                rlD.isSelected = true
            }
            bookmarknow.setOnClickListener {
                QuestionIndicationSheet(qstList).apply {
                    show(supportFragmentManager, "QuestionIndicationSheet")
                }
                // successToast("Page BookMarked SuccessFully")

            }

//            tvQuestionOf.setOnClickListener {
//                QuestionIndicationSheet(qstList).apply {
//                    show(supportFragmentManager, "QuestionIndicationSheet")
//                }
//            }

            tvFinish.setOnClickListener {
                AlertDialog.Builder(this@TestActivity)
                    .setTitle("Finish Test")
                    .setMessage("Are you sure you want to finish this test?")
                    .setPositiveButton(
                        "Yes"
                    ) { _, _ -> finishTest() }
                    .setNegativeButton(
                        "No"
                    ) { _, _ -> }
                    .show()
            }
        }
    }

    private fun showEnabled(pos: Int) {
        qstList[position].childOption.forEachIndexed { index, childOption ->
            childOption.isSelected = pos == index
        }
//        qstList[position].childOption[0].isSelected = false
//        qstList[position].childOption[1].isSelected = false
//        qstList[position].childOption[2].isSelected = false
//        qstList[position].childOption[3].isSelected = true
    }

    private fun finishTest() {
        var attempt = 0
        var skipped = 0
        var revised = 0


        for (qst in qstList) {
            if (qst.attempted) {
                attempt++
            }
            if (qst.skipped) {
                skipped++
            }
            if (qst.revised) {
                revised++
            }
        }

        val notAttempt = qstList.size - (attempt + skipped + revised)


        val intent = Intent(
            this@TestActivity,
            CongratulationActivity::class.java
        )
        intent.putExtra("boardId", boardId)
        intent.putExtra("testId", testId)
        intent.putExtra("attempted", attempt.toString())
        intent.putExtra("skipped", skipped.toString())
        intent.putExtra("not_attempted", notAttempt.toString())
        intent.putExtra("timing", timingSec.toString())
        intent.putExtra("data", qstList)
        startActivity(intent)
        finish()
    }

    private fun setDisabled() {
        binding?.rlA?.isSelected = false
        binding?.rlB?.isSelected = false
        binding?.rlC?.isSelected = false
        binding?.rlD?.isSelected = false
    }

    private fun checkAttempted(): Boolean {
        return binding?.rlA?.isSelected!! ||
                binding?.rlB?.isSelected!! ||
                binding?.rlC?.isSelected!! ||
                binding?.rlD?.isSelected!!
    }

    private fun startTimer() {
        val timeMillis = (totalMin.toInt() * 1000 * 60).toLong()
        val timer = object : CountDownTimer(timeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                val seconds = millisUntilFinished / 1000 % 60
                val minutes = (millisUntilFinished / (1000 * 60) % 60)


                val time = twoDigitString(minutes) + " : " + twoDigitString(seconds)

                timingSec = seconds

                binding?.tvTimer?.text = time

            }

            override fun onFinish() {

            }
        }
        timer.start()
    }

    private fun showData(qst: Qst) {
        binding?.apply {

            val questionNumber = position + 1
            tvQuestionOf.text = "$questionNumber of $totalQue"

            tvQuestionCount.text = "Question $questionNumber"

            tvQuestion.text = qst.qbsQuestions

            if (qst.childOption.size == 3) {
                rlD.gone()
            }

            if (qst.childOption.size == 2) {
                rlD.gone()
                rlC.gone()
            }

            qst.childOption.forEachIndexed { index, childOption ->
                if (index == 0) {
                    tvOptionA.text = childOption.option
                }
                if (index == 1) {
                    tvOptionB.text = childOption.option!!
                }
                if (index == 2) {
                    tvOptionC.text = childOption.option!!
                }
                if (index == 3) {
                    tvOptionD.text = childOption.option!!
                }
            }


        }
    }


    private fun twoDigitString(number: Long): String {
        if (number == 0L) {
            return "00"
        }
        return if (number / 10 == 0L) {
            "0$number"
        } else number.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onBackPressed() {

        AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit test?")
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    finish()
                }
            })
            .setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                }
            })
            .show()
    }
}