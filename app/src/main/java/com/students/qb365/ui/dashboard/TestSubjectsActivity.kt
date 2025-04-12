package com.students.qb365.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.students.qb365.databinding.ActivityTestSubjectsBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.dashboard.adapter.TestSubjectsAdapter
import com.students.qb365.ui.dashboard.model.Subject
import com.students.qb365.ui.dashboard.model.TestSubjects
import com.students.qb365.ui.subject_mode.SubjectChaptersActivity
import com.students.qb365.ui.subject_mode.WriteTestActivity
import com.students.qb365.ui.subject_mode.writeTest.ExamInstructionActivity
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestSubjectsActivity : AppCompatActivity(), OnClickListener<Subject> {

    private var binding: ActivityTestSubjectsBinding? = null

    private val model: MainViewModel by viewModels()

    private var boardId = ""

    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestSubjectsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        boardId = SharedPrefs.getString(this, SharedPrefs.BOARD_ID)!!

        try {
            type = intent.getStringExtra("type")!!
        } catch (e: Exception) {
            type = ""
        }


        binding?.apply {
            ivBack.setOnClickListener {
                onBackPressed()
            }

            rvSubjects.layoutManager = GridLayoutManager(this@TestSubjectsActivity, 2)


            DialogUtil.showProgressDialog(this@TestSubjectsActivity)
            model.testSubjects(boardId, { testSubjects: TestSubjects ->
                DialogUtil.dismissProgressDialog()

                val adapter =
                    TestSubjectsAdapter(
                        testSubjects.data?.subject!!,
                        this@TestSubjectsActivity
                    )
                rvSubjects.adapter = adapter

            }, { s ->
                DialogUtil.dismissProgressDialog()
            })

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(t: Subject, position: Int) {
        if (type.isEmpty()) {
            val intent = Intent(this, WriteTestActivity::class.java)
            intent.putExtra("sub_id", t.subjectId)
            intent.putExtra("boardId", boardId)
            intent.putExtra("sub_name", t.subjectName)
            startActivity(intent)
        } else {
            val intent = Intent(
                this,
                SubjectChaptersActivity::class.java
            )
            intent.putExtra("sub_id", t.subjectId)
            intent.putExtra("type", "test")
            intent.putExtra("sub_name", t.subjectName)
            intent.putExtra("boardId", boardId)
            startActivity(intent)
        }
    }
}