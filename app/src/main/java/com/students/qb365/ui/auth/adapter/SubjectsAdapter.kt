package com.students.qb365.ui.auth.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.students.qb365.databinding.RvBoardItemLayoutBinding
import com.students.qb365.databinding.RvSubjectsItemLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.model.*
import com.students.qb365.utils.load

class SubjectsAdapter(
    private val list: ArrayList<SubjectsDetails>,
    private val typeID: String?,
    private val onSubjectClickListener: OnSubjectClickListener
) :
    RecyclerView.Adapter<SubjectsAdapter.Viewholder>() {

    inner class Viewholder(private val binding: RvSubjectsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (typeID == "1") {
                    //Subject wise
                    if (list[adapterPosition].isSelected) {
                        list[adapterPosition].isSelected = false
                        onSubjectClickListener.onSubjectRemove(list[adapterPosition])

                    } else {
                        onSubjectClickListener.onSubjectAdd(list[adapterPosition])
                        list[adapterPosition].isSelected = true
                    }
                    notifyItemChanged(adapterPosition)

                } else {
                    //Chapter Wise
                    for (i in list.indices) {

                        list[i].isSelected = false

                        if (i == adapterPosition) {
                            list[i].isSelected = true
                            onSubjectClickListener.onChapter(list[i])
                        }
                    }
                    notifyDataSetChanged()

                }

            }
        }

        fun bindData(boardData: SubjectsDetails) {
            binding.tvTitle.text = boardData.qbsSubName
            binding.image.load(boardData.image!!)
            binding.clMain.isSelected = boardData.isSelected
        }


    }


    interface OnSubjectClickListener {
        fun onSubjectAdd(boardData: SubjectsDetails)
        fun onSubjectRemove(boardData: SubjectsDetails)
        fun onChapter(boardData: SubjectsDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvSubjectsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}