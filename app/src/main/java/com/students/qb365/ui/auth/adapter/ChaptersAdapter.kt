package com.students.qb365.ui.auth.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvBoardItemLayoutBinding
import com.students.qb365.ui.auth.model.*

class ChaptersAdapter(
    private val list: ArrayList<ChaptersDetails>,
    private val onSubjectClickListener: OnChaptersClickListener
) :
    RecyclerView.Adapter<ChaptersAdapter.Viewholder>() {

    inner class Viewholder(private val binding: RvBoardItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (list[adapterPosition].isSelected) {
                    list[adapterPosition].isSelected = false
                    onSubjectClickListener.onChapterRemove(list[adapterPosition])

                } else {
                    onSubjectClickListener.onChapterAdd(list[adapterPosition])
                    list[adapterPosition].isSelected = true
                }
                notifyItemChanged(adapterPosition)

            }
        }

        fun bindData(boardData: ChaptersDetails, position: Int) {
            binding.tvTitle.text = "${position + 1}. " + boardData.qbsChapterName
            binding.clMain.isSelected = boardData.isSelected
        }


    }


    interface OnChaptersClickListener {
        fun onChapterAdd(chapter: ChaptersDetails)
        fun onChapterRemove(chapter: ChaptersDetails)
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