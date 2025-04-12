package com.students.qb365.ui.subject_mode.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.RvPracticeQuestionLayoutBinding
import com.students.qb365.ui.subject_mode.practiceQuestion.model.PracticeQuestionData
import com.students.qb365.utils.gone
import com.students.qb365.utils.show
import java.util.ArrayList

class PractiseQuestionAdapter : RecyclerView.Adapter<PractiseQuestionAdapter.Viewholer>() {

    private var list: ArrayList<PracticeQuestionData> = arrayListOf()

    class Viewholer(val binding: RvPracticeQuestionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindData(data: PracticeQuestionData) {

            binding.apply {

                tvQuestion.text = "${adapterPosition + 1}. " + data.question

                val optionList = data.optionInDetails

                if (optionList.size > 0) {
                    llOption.show()
                    llAnswer.gone()

                    optionList.forEachIndexed { index, optionInDetails ->
                        when (index) {
                            0 -> {
                                tvOptionA.text = "A. " + optionInDetails.value
                                tvOptionA.isSelected = optionInDetails.result?.isNotEmpty()!!
                            }
                            1 -> {
                                tvOptionB.text = "B. " + optionInDetails.value
                                tvOptionB.isSelected = optionInDetails.result?.isNotEmpty()!!
                            }
                            2 -> {
                                tvOptionC.text = "C. " + optionInDetails.value
                                tvOptionC.isSelected = optionInDetails.result?.isNotEmpty()!!
                            }
                            3 -> {
                                tvOptionD.text = "D. " + optionInDetails.value
                                tvOptionD.isSelected = optionInDetails.result?.isNotEmpty()!!
                            }
                        }
                    }

//                    tvOptionA.text = "A. " + optionList[0].value
//                    tvOptionB.text = "B. " + optionList[1].value
//                    tvOptionC.text = "C. " + optionList[2].value
//                    tvOptionD.text = "D. " + optionList[3].value
//
//                    tvOptionA.isSelected = optionList[0].result?.isNotEmpty()!!
//                    tvOptionB.isSelected = optionList[1].result?.isNotEmpty()!!
//                    tvOptionC.isSelected = optionList[2].result?.isNotEmpty()!!
//                    tvOptionD.isSelected = optionList[3].result?.isNotEmpty()!!

                } else {
                    llAnswer.show()
                    llOption.gone()

                    tvAnswer.text = data.solution!!
                }

                tvQuestionType.text = data.questionType!!

            }

        }


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PractiseQuestionAdapter.Viewholer {
        val binding = RvPracticeQuestionLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PractiseQuestionAdapter.Viewholer(binding)
    }

    override fun onBindViewHolder(holder: Viewholer, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size


    fun addData(list: ArrayList<PracticeQuestionData>) {
        this.list = list
        notifyDataSetChanged()

    }
}