package com.students.qb365.ui.subject_mode.practiceQuestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.R
import com.students.qb365.databinding.ActivityPracticeQuestionBinding
import com.students.qb365.ui.subject_mode.adapter.PractiseQuestionAdapter
import com.students.qb365.ui.subject_mode.model.Qstarr
import com.students.qb365.ui.subject_mode.practiceQuestion.model.PracticeQuestionData
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.utils.DialogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class PracticeQuestionActivity : AppCompatActivity() {

    private var binding: ActivityPracticeQuestionBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var subID = ""
    private var chapterID = ""
    private var boardID = ""
    private var quesType = ""
    private var chapterName = ""
    private var position by Delegates.notNull<Int>()
    private lateinit var list: ArrayList<Qstarr>
    private lateinit var practiceAdapter: PractiseQuestionAdapter
    private var queTypeList = ArrayList<String>()
    private var ansStatus = "1"
    private val gotoList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeQuestionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        boardID = intent.getStringExtra("boardID")!!
        chapterName = intent.getStringExtra("chapterName")!!
        chapterID = intent.getStringExtra("chapterID")!!
        subID = intent.getStringExtra("subID")!!
        list = intent.getParcelableArrayListExtra("list")!!
        quesType = intent.getStringExtra("quesType")!!
        position = intent.getIntExtra("position", 0)

        binding?.apply {

            initQuesTypeSpinner()
            initBookCreativeSpinner()

            practiceAdapter = PractiseQuestionAdapter()
            rvQuestions.layoutManager = LinearLayoutManager(this@PracticeQuestionActivity)
            rvQuestions.adapter = practiceAdapter

//            getData(quesType)

            ivBack.setOnClickListener {
                onBackPressed()
            }

        }
    }

    private fun initGotoQueSpinner(data: ArrayList<PracticeQuestionData>) {
        gotoList.clear()

        data.forEachIndexed { index, qst ->
            gotoList.add((index + 1).toString())
        }

        val adapter = ArrayAdapter(
            this,
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
                    binding?.rvQuestions?.scrollToPosition(gotoList[position].toInt() - 1)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

    }

    private fun initBookCreativeSpinner() {

        val list2 = arrayListOf<String>()
        list2.add("All")
        list2.add("NCERT")
        list2.add("Creative")

        val adapter = ArrayAdapter(
            this@PracticeQuestionActivity,
            R.layout.simple_spinner_item, list2
        )
        adapter.setDropDownViewResource(
            R.layout
                .simple_spinner_dropdown_item
        )

        binding?.spnAnsStatus?.adapter = adapter
        binding?.spnAnsStatus?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    ansStatus = when (position) {
                        1 -> {
                            "0"
                        }
                        2 -> {
                            "1"
                        }
                        else -> {
                            "1"
                        }
                    }
                    getData()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    private fun initQuesTypeSpinner() {
        list.forEachIndexed { index, qst ->
            queTypeList.add(qst.name!!)
        }

        val adapter = ArrayAdapter(
            this@PracticeQuestionActivity,
            R.layout.simple_spinner_item, queTypeList
        )
        adapter.setDropDownViewResource(
            R.layout
                .simple_spinner_dropdown_item
        )

        binding?.spnQuestionType?.adapter = adapter
        binding?.spnQuestionType?.setSelection(position)
        binding?.spnQuestionType?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    quesType = list[position].qbsQsTypeId!!
                    getData()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    private fun getData() {
        DialogUtil.showProgressDialog(this@PracticeQuestionActivity)
        model.practiseQuestion(subID, chapterID, quesType, ansStatus, boardID,
            { practiceQuestion ->
                DialogUtil.dismissProgressDialog()

                practiceAdapter.addData(practiceQuestion.data)
                initGotoQueSpinner(practiceQuestion.data)

            }, { s ->
                DialogUtil.dismissProgressDialog()
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}