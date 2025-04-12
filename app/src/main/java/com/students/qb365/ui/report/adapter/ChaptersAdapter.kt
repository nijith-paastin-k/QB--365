package com.students.qb365.ui.report.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvBoardItemLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.model.*
import com.students.qb365.ui.report.model.ChapterReportData

class ChaptersAdapter(
    private val list: ArrayList<ChapterReportData>,
    private val onClickListener: OnClickListener<ChapterReportData>
) :
    RecyclerView.Adapter<ChaptersAdapter.Viewholder>() {

    inner class Viewholder(private val binding: RvBoardItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener.onClick(list[adapterPosition],adapterPosition)
            }
        }

        fun bindData(boardData: ChapterReportData, position: Int) {
            binding.tvTitle.text = "${position + 1}. " + boardData.name
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvBoardItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}