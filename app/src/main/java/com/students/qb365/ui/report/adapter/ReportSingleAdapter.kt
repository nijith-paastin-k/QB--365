package com.students.qb365.ui.report.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.R
import com.students.qb365.databinding.RvBoardItemLayoutBinding
import com.students.qb365.databinding.RvChapterListItemLayoutBinding
import com.students.qb365.databinding.RvSearchLayoutBinding
import com.students.qb365.databinding.RvTestWiseReportSubjectsLayoutBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.dashboard.model.SearchData
import com.students.qb365.ui.report.model.Subject
import com.students.qb365.ui.report.model.TestTotal
import com.students.qb365.utils.loadCircular
import kotlin.math.roundToInt


class ReportSingleAdapter(
    private val list: ArrayList<TestTotal>,
    private val onClickListener: OnClickListener<TestTotal>
) :
    RecyclerView.Adapter<ReportSingleAdapter.Viewholder>() {


    inner class Viewholder(private val binding: RvChapterListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener.onClick(list[adapterPosition], adapterPosition)
            }
        }

        fun bindData(data: TestTotal) {

            binding.tvTitle.text = data.title
            binding.tvTotalQuestions.text = "${data.total} Tests"

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = RvChapterListItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size


}