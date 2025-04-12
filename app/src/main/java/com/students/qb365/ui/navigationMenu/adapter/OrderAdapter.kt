package com.students.qb365.ui.navigationMenu.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.LayoutOrderDetailsBinding

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.Viewholder>() {


    class Viewholder(binding : LayoutOrderDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindData() {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = LayoutOrderDetailsBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int = 1
}