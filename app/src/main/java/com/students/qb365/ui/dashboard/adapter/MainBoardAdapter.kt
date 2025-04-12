package com.students.qb365.ui.dashboard.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvBoardListLayoutBinding
import com.students.qb365.databinding.RvHeaderSectionLayoutBinding
import com.students.qb365.ui.auth.model.DashboardDetails
import com.students.qb365.ui.subject_mode.SubjectModeActivity

class MainBoardAdapter(val context: Context, private val list: ArrayList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), BoardAdapter.OnSubjectClickListener {

    inner class ListViewholder(private val binding: RvBoardListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                rvBoard.layoutManager = LinearLayoutManager(
                    itemView.context,
                    RecyclerView.HORIZONTAL, false
                )

            }
        }

        fun bindData(arrayList: ArrayList<DashboardDetails>) {

            val adapter = BoardAdapter(arrayList, this@MainBoardAdapter)
            binding.rvBoard.adapter = adapter

        }


    }


    inner class HeaderViewholder(val binding: RvHeaderSectionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(s: String) {
            binding.tvHeader.text = s
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val binding =
                RvHeaderSectionLayoutBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            return HeaderViewholder(binding)
        } else {
            val binding =
                RvBoardListLayoutBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            return ListViewholder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == 0) {
            (holder as HeaderViewholder).bindData(list[position] as String)
        } else {
            (holder as ListViewholder).bindData(list[position] as ArrayList<DashboardDetails>)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is String) {
            0
        } else {
            1
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onClick(data: DashboardDetails) {
        val intent = Intent(context, SubjectModeActivity::class.java)
        intent.putExtra("sub_id", data.qbsSubId)
        intent.putExtra("sub_name", data.qbsSubName)
        intent.putExtra("boardId", data.boardType.toString())
        context.startActivity(intent)
    }
}