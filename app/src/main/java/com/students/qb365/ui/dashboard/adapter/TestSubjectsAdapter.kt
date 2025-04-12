package com.students.qb365.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.students.qb365.databinding.RvBoardItemLayoutBinding
import com.students.qb365.databinding.RvSubjectsItemLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.model.*
import com.students.qb365.ui.dashboard.model.Subject
import com.students.qb365.utils.load

class TestSubjectsAdapter(
    private val subList: ArrayList<Subject>,
    private val onClickListener: OnClickListener<Subject>
) :
    RecyclerView.Adapter<TestSubjectsAdapter.Viewholder>() {

    inner class Viewholder(private val binding: RvSubjectsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener.onClick(subList[adapterPosition],adapterPosition)
            }
        }

        fun bindData(boardData: Subject) {
            binding.tvTitle.text = boardData.subjectName
            binding.image.load(boardData.subjectImage)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvSubjectsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(subList[position])
    }

    override fun getItemCount(): Int = subList.size
}