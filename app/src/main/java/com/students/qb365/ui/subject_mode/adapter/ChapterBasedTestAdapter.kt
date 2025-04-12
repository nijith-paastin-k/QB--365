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
import com.students.qb365.ui.subject_mode.model.ChapterBased
import com.students.qb365.ui.subject_mode.model.Chapters

class ChapterBasedTestAdapter(
    private val list: ArrayList<ChapterBased>,
    private val onClickListener: OnClickListener<ChapterBased>
) :
    RecyclerView.Adapter<ChapterBasedTestAdapter.Viewholder>() {

    inner class Viewholder(private val binding: RvBoardItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener.onClick(list[adapterPosition], adapterPosition)
            }
        }

        fun bindData(boardData: ChapterBased, position: Int) {
            binding.tvTitle.text = boardData.testName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvBoardItemLayoutBinding.inflate(
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