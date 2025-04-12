package com.students.qb365.ui.subject_mode.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvBoardItemLayoutBinding
import com.students.qb365.databinding.RvSubjectChapterItemLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.model.*
import com.students.qb365.ui.subject_mode.model.Chapters
import com.students.qb365.ui.subject_mode.model.Total

class WriteTestAdapter(
    private val list: ArrayList<Total>,
    private val onClickListener: OnClickListener<Total>
) :
    RecyclerView.Adapter<WriteTestAdapter.Viewholder>() {

    inner class Viewholder(private val binding: RvSubjectChapterItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener.onClick(list[adapterPosition], adapterPosition)
            }
        }

        fun bindData(boardData: Total, position: Int) {
            binding.tvTitle.text = boardData.name
            binding.tvTotalQuestions.text = boardData.total + " Tests"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvSubjectChapterItemLayoutBinding.inflate(
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