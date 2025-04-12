package com.students.qb365.ui.subject_mode.writeTest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.R
import com.students.qb365.databinding.RvQuestionIndicatorLayoutBinding
import com.students.qb365.ui.subject_mode.writeTest.model.Qst

class QuestionIndicatorAdapter(private val qstList: ArrayList<Qst>) :
    RecyclerView.Adapter<QuestionIndicatorAdapter.Viewholder>() {


    class Viewholder(private val binding: RvQuestionIndicatorLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val context = itemView.context

        fun bindData(data: Qst, position: Int) {

            binding.tvNumber.text = (position + 1).toString()

            if (data.attempted) {
                binding.tvNumber.isSelected = true
                binding.tvNumber.background = context.getDrawable(R.drawable.green_background)
            } else if (data.skipped) {
                binding.tvNumber.isSelected = true
                binding.tvNumber.background = context.getDrawable(R.drawable.red_background)
            } else if (data.revised) {
                binding.tvNumber.isSelected = true
                binding.tvNumber.background = context.getDrawable(R.drawable.orange_background)
            } else if (data.notAttempted) {
                binding.tvNumber.isSelected = false
                binding.tvNumber.background =
                    context.getDrawable(R.drawable.border_background)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = RvQuestionIndicatorLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(qstList[position], position)
    }

    override fun getItemCount(): Int = qstList.size
}