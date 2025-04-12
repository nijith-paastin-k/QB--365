package com.students.qb365.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivitySubjectsBinding
import com.students.qb365.ui.auth.adapter.SubjectsAdapter
import com.students.qb365.ui.auth.model.SubjectsDetails
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.gone
import com.students.qb365.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectsActivity : AppCompatActivity(), SubjectsAdapter.OnSubjectClickListener {

    private var binding: ActivitySubjectsBinding? = null

    private val model: AuthViewModel by viewModels()

    private var packID = ""
    private var boardId = ""
    private var typeID = ""

    private var subList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        packID = intent.getStringExtra("packID")!!
        boardId = intent.getStringExtra("boardId")!!
        typeID = intent.getStringExtra("typeID")!!
        val name = intent.getStringExtra("name")


        binding?.apply {

            if (typeID == "1") {
                btnContinue.show()
            } else {
                btnContinue.gone()
            }

            rvSubjects.layoutManager = GridLayoutManager(this@SubjectsActivity, 2)

            DialogUtil.showProgressDialog(this@SubjectsActivity)
            model.getSubjects(boardId!!, packID!!, { subjects ->
                DialogUtil.dismissProgressDialog()

                val adapter =
                    SubjectsAdapter(subjects.subjects[0].details, typeID, this@SubjectsActivity)
                rvSubjects.adapter = adapter


            }, { s ->
                DialogUtil.dismissProgressDialog()

            })

            ivBack.setOnClickListener {
                onBackPressed()
            }

            btnContinue.setOnClickListener {
                if (subList.size < 1) {
                    errorToast("Select At Least One Subject")
                    return@setOnClickListener
                }

                val intent = Intent(this@SubjectsActivity, PaymentActivity::class.java)
                intent.putExtra("typeID", typeID)
                intent.putExtra("packID", packID)
                intent.putExtra("boardId", boardId)
                intent.putExtra("subjectList", subList)
                startActivity(intent)

            }

            ivBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onSubjectAdd(boardData: SubjectsDetails) {
        subList.add(boardData.qbsSubId!!)
    }

    override fun onSubjectRemove(boardData: SubjectsDetails) {
        subList.remove(boardData.qbsSubId!!)
    }

    override fun onChapter(boardData: SubjectsDetails) {
        val intent = Intent(this, ChaptersActivity::class.java)
        intent.putExtra("typeID", typeID)
        intent.putExtra("packID", packID)
        intent.putExtra("boardId", boardId)
        intent.putExtra("subjectId", boardData.qbsSubId)
        intent.putExtra("subjectName", boardData.qbsSubName)
        startActivity(intent)
    }
}