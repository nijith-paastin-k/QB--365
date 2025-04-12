package com.students.qb365.ui.subject_mode.writeTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.R
import com.students.qb365.databinding.ActivityTestSolutionBinding
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.ui.subject_mode.writeTest.adapter.SolutionAdapter
import com.students.qb365.ui.subject_mode.writeTest.model.Qst
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestSolutionActivity : AppCompatActivity() {

    private var binding: ActivityTestSolutionBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var boardId = ""
    private var testId = ""
    private var qstList = arrayListOf<Qst>()
    private var insertId = ""
    private var oldList = arrayListOf<Qst>()
    private var newList = arrayListOf<Qst>()

    private val gotoList = ArrayList<String>()
    private val ansStatusList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestSolutionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        testId = intent.getStringExtra("testId")!!
        boardId = intent.getStringExtra("boardId")!!
        insertId = intent.getStringExtra("insertId")!!
        qstList = intent.getParcelableArrayListExtra("data")!!

        binding?.apply {

            rvQuestions.layoutManager = LinearLayoutManager(this@TestSolutionActivity)
            val solutionAdapter = SolutionAdapter()
            rvQuestions.adapter = solutionAdapter

            ansStatusList.add("All")
            ansStatusList.add("Correct")
            ansStatusList.add("Wrong")
            ansStatusList.add("Skipped")


            setGotoQuestion(qstList)


            val ansStatusAdapter = ArrayAdapter(
                this@TestSolutionActivity,
                R.layout.simple_spinner_item, ansStatusList
            )
            ansStatusAdapter.setDropDownViewResource(
                R.layout
                    .simple_spinner_dropdown_item
            )


            spnAnsStatus.adapter = ansStatusAdapter
            spnAnsStatus.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                        val old = arrayListOf<Qst>()
                        val new = arrayListOf<Qst>()

                        when (position) {
                            0 -> {
                                new.addAll(newList)
                                old.addAll(oldList)
                                setGotoQuestion(new)
                            }
                            1 ->{
                                //Correct
                                newList.forEachIndexed { index, qst ->

                                    qst.childOption.forEachIndexed { i, childOption ->
                                        if (childOption.true_answer == oldList[index].childOption[i].isSelected) {
                                            new.add(qst)
                                            old.add(oldList[index])
                                        }
                                    }
                                }
                                setGotoQuestion(new)
                            }

                            2 ->{
                                //Wrong
                                newList.forEachIndexed { index, qst ->

                                    qst.childOption.forEachIndexed { i, childOption ->
                                        if (childOption.true_answer != oldList[index].childOption[i].isSelected) {
                                            new.add(qst)
                                            old.add(oldList[index])
                                        }
                                    }
                                }
                                setGotoQuestion(new)
                            }

                            3 ->{
                                //Skipped
                                qstList.forEachIndexed { index, qst ->
                                    if (qst.skipped){
                                        new.add(qst)
                                        old.add(qst)
                                    }
                                }
                                setGotoQuestion(new)
                            }
                        }

                        solutionAdapter.addData(new, old)
//                        setGotoQuestion(new)

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }


            ivBack.setOnClickListener {
                onBackPressed()
            }


            DialogUtil.showProgressDialog(this@TestSolutionActivity)
            model.answerKey(boardId, testId, insertId, { writeTest ->
                DialogUtil.dismissProgressDialog()

                newList = writeTest.data?.qst!!
                oldList = qstList

                solutionAdapter.addData(writeTest.data?.qst!!, qstList)
                setGotoQuestion(newList)

            }, { s ->
                DialogUtil.dismissProgressDialog()

            })

        }

    }

    private fun setGotoQuestion(qstList: ArrayList<Qst>) {

        gotoList.clear()

        qstList.forEachIndexed { index, qst ->
            gotoList.add((index + 1).toString())
        }

        val adapter = ArrayAdapter(
            this@TestSolutionActivity,
            R.layout.simple_spinner_item, gotoList
        )
        adapter.setDropDownViewResource(
            R.layout
                .simple_spinner_dropdown_item
        )

        binding?.spnGoToAns?.adapter = adapter
        binding?.spnGoToAns?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binding?.rvQuestions?.smoothScrollToPosition(gotoList[position].toInt() - 1)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}