package com.students.qb365.ui.navigationMenu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvTodayTestItemLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.navigationMenu.model.Test

class ForYouAdapter(
    private val test: ArrayList<Test>,
    private val onClickListener: OnClickListener<Test>
) : RecyclerView.Adapter<ForYouAdapter.Viewholder>() {

    inner class Viewholder(val binding: RvTodayTestItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnStart.setOnClickListener {
                onClickListener.onClick(test[adapterPosition], adapterPosition)
            }
        }

        fun bindData(test: Test) {
            binding.tvTitle.text = test.testName
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvTodayTestItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(test[position])
    }

    override fun getItemCount(): Int = test.size
}