package com.students.qb365.ui.subject_mode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityWriteTestBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.subject_mode.adapter.WriteTestAdapter
import com.students.qb365.ui.subject_mode.model.Total
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteTestActivity : AppCompatActivity(), OnClickListener<Total> {

    private var binding: ActivityWriteTestBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var sub_id = ""
    private var boardId = ""
    private var sub_name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteTestBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        sub_id = intent.getStringExtra("sub_id")!!
        boardId = intent.getStringExtra("boardId")!!
        sub_name = intent.getStringExtra("sub_name")!!

        binding?.apply {

            ivBack.setOnClickListener { onBackPressed() }

            tvHeader.text = sub_name

            rvChapters.layoutManager = LinearLayoutManager(this@WriteTestActivity)
            DialogUtil.showProgressDialog(this@WriteTestActivity)

            model.getWriteTestType(sub_id, boardId, { writeTestType ->
                DialogUtil.dismissProgressDialog()

                val writeTestAdapter = WriteTestAdapter(
                    writeTestType.data?.total!!,
                    this@WriteTestActivity
                )
                rvChapters.adapter = writeTestAdapter


            }, { s ->
                DialogUtil.dismissProgressDialog()

            })


        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(t: Total, position: Int) {
        if (t.name == "Subject Based Test") {
            val intent = Intent(this,
                SubjectBasedTestActivity::class.java)
            intent.putExtra("sub_id", sub_id)
            intent.putExtra("boardId", boardId)
            startActivity(intent)
        } else if (t.name == "Chapter Based Test") {
            val intent = Intent(this,
                ChapterListTestActivity::class.java)
            intent.putExtra("sub_id", sub_id)
            intent.putExtra("boardId", boardId)
            startActivity(intent)
        } else {
            openActivity(TodayTestActivity::class.java)
        }
    }
}