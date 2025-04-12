package com.students.qb365.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvTestPackItemLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.model.TestPack
import com.students.qb365.utils.load

class TestPackAdapter(
    private val list: ArrayList<TestPack>,
    private val onClickListener: OnClickListener<TestPack>
) :
    RecyclerView.Adapter<TestPackAdapter.Viewholder>() {


    inner class Viewholder(private val binding: RvTestPackItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.root.setOnClickListener {
                onClickListener.onClick(list[adapterPosition], adapterPosition)
            }

        }

        fun bindData(testPack: TestPack) {
            binding.tvTitle.text = testPack.title
            binding.tvSubTitle.text = testPack.description
            binding.image.load(testPack.image!!)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvTestPackItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Viewholder(binding)

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}