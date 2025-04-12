package com.students.qb365.ui.navigationMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.databinding.ActivityForYouBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.dashboard.MainViewModel
import com.students.qb365.ui.navigationMenu.adapter.ForYouAdapter
import com.students.qb365.ui.navigationMenu.model.Test
import com.students.qb365.ui.subject_mode.SubjectChaptersActivity
import com.students.qb365.ui.subject_mode.writeTest.ExamInstructionActivity
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForYouActivity : AppCompatActivity(), OnClickListener<Test> {

    private var binding: ActivityForYouBinding? = null

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForYouBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            rvTodayTest.layoutManager = LinearLayoutManager(this@ForYouActivity)

            DialogUtil.showProgressDialog(this@ForYouActivity)
            val board = SharedPrefs.getString(this@ForYouActivity, SharedPrefs.BOARD_ID)
            model.forYou(board!!, { forYou ->
                DialogUtil.dismissProgressDialog()

                val adapter = ForYouAdapter(forYou.data?.test!!,this@ForYouActivity)
                rvTodayTest.adapter = adapter

            }, { s ->
                DialogUtil.dismissProgressDialog()
            })


            btnStart.setOnClickListener{
                val intent = Intent(
                    this@ForYouActivity,
                    SubjectChaptersActivity::class.java
                )
                intent.putExtra("sub_id", "55")
                intent.putExtra("sub_name", "Physics")
                intent.putExtra("boardId", board)
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

    override fun onClick(t: Test, position: Int) {
        val board = SharedPrefs.getString(this@ForYouActivity, SharedPrefs.BOARD_ID)
        val intent = Intent(this, ExamInstructionActivity::class.java)
        intent.putExtra("testId", t.testId)
        intent.putExtra("boardId", board)
        intent.putExtra("testName", t.testName)
        startActivity(intent)
    }
}