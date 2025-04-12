package com.students.qb365.ui.auth.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvBoardItemLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.model.BoardData
import com.students.qb365.ui.auth.model.ClassesData
import com.students.qb365.ui.auth.model.Details
import com.students.qb365.ui.auth.model.Type

class PurchaseTypeAdapter(
    private val list: ArrayList<Type>,
    private val onClickListener: OnClickListener<Type>
) :
    RecyclerView.Adapter<PurchaseTypeAdapter.Viewholder>() {

    inner class Viewholder(private val binding: RvBoardItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener.onClick(list[adapterPosition], adapterPosition)
            }
        }

        fun bindData(boardData: Type) {
            binding.tvTitle.text = boardData.name
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvBoardItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}