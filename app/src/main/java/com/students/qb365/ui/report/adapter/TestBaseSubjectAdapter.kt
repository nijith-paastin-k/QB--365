package com.students.qb365.ui.report.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.R
import com.students.qb365.databinding.RvSearchLayoutBinding
import com.students.qb365.databinding.RvTestWiseReportSubjectsLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.dashboard.model.SearchData
import com.students.qb365.ui.report.model.Subject
import com.students.qb365.utils.loadCircular
import kotlin.math.roundToInt


class TestBaseSubjectAdapter(
    private val list: ArrayList<Subject>,
    private val onClickListener: OnClickListener<Subject>
) :
    RecyclerView.Adapter<TestBaseSubjectAdapter.Viewholder>() {


    inner class Viewholder(private val binding: RvTestWiseReportSubjectsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener.onClick(list[adapterPosition], adapterPosition)
            }
        }

        fun bindData(data: Subject) {

            binding.tvSubjectName.text = data.qbsSubName
            binding.image.loadCircular(data.image!!, R.drawable.ic_user_placeholder)

            binding.tvTestChap.text = "${data.test} Tests - ${data.chapters} Chapters"

            binding.tvPercent.text = "${data.perc}% completed"
            binding.progressBar.progress = data.perc!!


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = RvTestWiseReportSubjectsLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size


}