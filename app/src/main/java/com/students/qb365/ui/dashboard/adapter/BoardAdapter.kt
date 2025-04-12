package com.students.qb365.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvHomeSubjectLayoutBinding
import com.students.qb365.ui.auth.model.DashboardDetails
import com.students.qb365.utils.load


class BoardAdapter(
    private val list: ArrayList<DashboardDetails>,
    private val onSubjectClickListener: OnSubjectClickListener
) :
    RecyclerView.Adapter<BoardAdapter.Viewholder>() {


    inner class Viewholder(private val binding: RvHomeSubjectLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.root.setOnClickListener {
                onSubjectClickListener.onClick(list[adapterPosition])
            }


        }

        fun bindData(dashboardDetails: DashboardDetails) {
            binding.apply {

                image.load(dashboardDetails.image!!)
                tvSubjectName.text = dashboardDetails.qbsSubName

            }

        }

    }

    interface OnSubjectClickListener {
        fun onClick(data: DashboardDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        val binding =
            RvHomeSubjectLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )

        return Viewholder(binding)

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}