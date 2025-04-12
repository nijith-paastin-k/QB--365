package com.students.qb365.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityChaptersBinding
import com.students.qb365.ui.auth.adapter.ChaptersAdapter
import com.students.qb365.ui.auth.model.Chapters
import com.students.qb365.ui.auth.model.ChaptersDetails
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import com.students.qb365.utils.errorToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChaptersActivity : AppCompatActivity(), ChaptersAdapter.OnChaptersClickListener {


    private var binding: ActivityChaptersBinding? = null

    private val model: AuthViewModel by viewModels()

    private var packID = ""
    private var boardId = ""
    private var typeID = ""
    private var subId = ""
    private var subName = ""

    private var chapList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChaptersBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        packID = intent.getStringExtra("packID")!!
        boardId = intent.getStringExtra("boardId")!!
        typeID = intent.getStringExtra("typeID")!!
        subId = intent.getStringExtra("subjectId")!!
        subName = intent.getStringExtra("subjectName")!!

        binding?.apply {
            tvHeader.text = subName

            rvChapters.layoutManager = LinearLayoutManager(this@ChaptersActivity)

            DialogUtil.showProgressDialog(this@ChaptersActivity)
            model.getChapters(boardId, subId, { chapters: Chapters ->
                DialogUtil.dismissProgressDialog()

                chapCount.text = chapters.chapters?.details?.size.toString() + " Chapters"

                val chaptersAdapter = ChaptersAdapter(
                    chapters.chapters?.details!!,
                  this@ChaptersActivity
                )

                rvChapters.adapter = chaptersAdapter

            }, { s ->

            })

            btnContinue.setOnClickListener {
                if (chapList.size < 1) {
                    errorToast("Select At Least One Chapter")
                    return@setOnClickListener
                }

                val intent = Intent(this@ChaptersActivity, PaymentActivity::class.java)
                intent.putExtra("typeID", typeID)
                intent.putExtra("packID", packID)
                intent.putExtra("boardId", boardId)
                intent.putExtra("subjectId", subId)
                intent.putExtra("chapterList", chapList)
                startActivity(intent)
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onChapterAdd(chapter: ChaptersDetails) {
        chapList.add(chapter.qbsChapterId!!)
    }

    override fun onChapterRemove(chapter: ChaptersDetails) {
        chapList.remove(chapter.qbsChapterId!!)
    }
}