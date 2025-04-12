package com.students.qb365.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityStandardBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.adapter.ClassesAdapter
import com.students.qb365.ui.auth.model.Details
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StandardActivity : AppCompatActivity(), OnClickListener<Details> {

    private var binding: ActivityStandardBinding? = null

    private val model: AuthViewModel by viewModels()

    private lateinit var boardId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStandardBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        boardId = intent.getStringExtra("boardId")!!
        val boardName = intent.getStringExtra("boardName")

        binding?.apply {

            tvHeader.text = boardName

            rvClasses.layoutManager = LinearLayoutManager(this@StandardActivity)

            DialogUtil.showProgressDialog(this@StandardActivity)
            model.getClasses(boardId!!, { classes ->
                DialogUtil.dismissProgressDialog()
                val adapter = ClassesAdapter(
                    classes.classes[0].details as ArrayList<Details>,
                    this@StandardActivity
                )
                rvClasses.adapter = adapter

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

    override fun onClick(t: Details, position: Int) {
        val intent = Intent(this, PurchaseTypeActivity::class.java)
        intent.putExtra("packID", t.packId)
        intent.putExtra("boardId", boardId)
        intent.putExtra("packName", t.packName)
        startActivity(intent)
    }
}