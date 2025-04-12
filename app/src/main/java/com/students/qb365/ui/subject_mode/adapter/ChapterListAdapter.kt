package com.students.qb365.ui.subject_mode.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvBoardItemLayoutBinding
import com.students.qb365.databinding.RvChapterListItemLayoutBinding
import com.students.qb365.databinding.RvSubjectChapterItemLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.model.*
import com.students.qb365.ui.subject_mode.model.Chapter
import com.students.qb365.ui.subject_mode.model.Chapters

class ChapterListAdapter(
    private val list: ArrayList<Chapter>,
    private val onClickListener: OnClickListener<Chapter>
) :
    RecyclerView.Adapter<ChapterListAdapter.Viewholder>() {

    inner class Viewholder(private val binding: RvChapterListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener.onClick(list[adapterPosition], adapterPosition)
            }
        }

        fun bindData(boardData: Chapter, position: Int) {
            binding.tvTitle.text = boardData.qbsChapterName
            binding.tvTotalQuestions.text = boardData.total + " Tests"
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvChapterListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}