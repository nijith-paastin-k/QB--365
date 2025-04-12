package com.students.qb365.ui.dashboard.adapter

import android.R
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvSearchLayoutBinding
import com.students.qb365.ui.dashboard.model.SearchData


class SearchAdapter(private val list: ArrayList<SearchData>) :
    RecyclerView.Adapter<SearchAdapter.Viewholder>() {

    init {
        setHasStableIds(true)
    }

    class Viewholder(val binding: RvSearchLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindData(searchData: SearchData) {

            binding.tvQuestion.text = searchData.question!!

            binding.tvAnswer.text = searchData.solution!!
            Log.e("Dataaaa", binding.tvAnswer.text)
            binding.tvSolution.text = "Solution"

        }


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = RvSearchLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size


}