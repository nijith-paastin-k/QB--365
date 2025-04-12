package com.students.qb365.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityBoardBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.adapter.BoardsAdapter
import com.students.qb365.ui.auth.model.BoardData
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardActivity : AppCompatActivity(), OnClickListener<BoardData> {

    private var binding: ActivityBoardBinding? = null

    private val model: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            rvBoards.layoutManager = LinearLayoutManager(this@BoardActivity)

            DialogUtil.showProgressDialog(this@BoardActivity)
            model.getBoards("Bearer AsJ4[pR3=bM5^gJ0]pS6.gI2\$hV5*uSA", { board ->

                DialogUtil.dismissProgressDialog()
                val adapter = BoardsAdapter(board.board as ArrayList<BoardData>, this@BoardActivity)
                rvBoards.adapter = adapter

            }, { s ->

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

    override fun onClick(t: BoardData, position: Int) {
        val intent = Intent(this, StandardActivity::class.java)
        intent.putExtra("boardId", t.id.toString())
        intent.putExtra("boardName", t.name)
        startActivity(intent)
    }
}