package com.students.qb365.ui.subject_mode.writeTest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.R
import com.students.qb365.databinding.RvSolutionItemLayoutBinding
import com.students.qb365.ui.subject_mode.writeTest.model.Qst

class SolutionAdapter() : RecyclerView.Adapter<SolutionAdapter.Viewholder>() {

    private var newQstList: ArrayList<Qst> = arrayListOf()
    private var oldList: ArrayList<Qst> = arrayListOf()

//    init {
//        setHasStableIds(true)
//    }

    inner class Viewholder(val binding: RvSolutionItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val greenBack = itemView.context.getDrawable(R.drawable.green_background)
        val redBack = itemView.context.getDrawable(R.drawable.red_background)
        val transBack = itemView.context.getDrawable(R.drawable.transparent_background)


        fun bindData(newQst: Qst, oldQst: Qst) {

            binding.apply {

                tvQuestion.text = "${adapterPosition + 1}. " + newQst.qbsQuestions

                newQst.childOption.forEachIndexed { index, childOption ->
                    when (index) {
                        0 -> {
                            tvOptionA.text = "A. " + childOption.option
                            if (childOption.wrong_answer == true) {
                                tvOptionA.isSelected = true
                                tvOptionA.background = redBack
                            }
                            if (childOption.true_answer == true) {
                                tvOptionA.isSelected = true
                                tvOptionA.background = greenBack
                            }
                        }
                        1 -> {
                            tvOptionB.text = "B. " + childOption.option
                            if (childOption.wrong_answer!!) {
                                tvOptionB.isSelected = true
                                tvOptionB.background = redBack
                            }
                            if (childOption.true_answer == true) {
                                tvOptionB.isSelected = true
                                tvOptionB.background = greenBack
                            }
                        }
                        2 -> {
                            tvOptionC.text = "C. " + childOption.option
                            if (childOption.wrong_answer == true) {
                                tvOptionC.isSelected = true
                                tvOptionC.background = redBack
                            }
                            if (childOption.true_answer == true) {
                                tvOptionC.isSelected = true
                                tvOptionC.background = greenBack
                            }
                        }
                        3 -> {
                            tvOptionD.text = "D. " + childOption.option
                            if (childOption.wrong_answer == true) {
                                tvOptionD.isSelected = true
                                tvOptionD.background = redBack
                            }
                            if (childOption.true_answer == true) {
                                tvOptionD.isSelected = true
                                tvOptionD.background = greenBack
                            }
                        }
                    }


                }


                /*newQst.childOption.forEachIndexed { index, childOption ->
                    if (childOption.user_checked == oldQst.childOption[index].isSelected) {
                        when (index) {
                            0 -> {
                                tvOptionA.isSelected = true
                                tvOptionA.background = greenBack
                            }
                            1 -> {
                                tvOptionB.isSelected = true
                                tvOptionB.background = greenBack
                            }
                            2 -> {
                                tvOptionC.isSelected = true
                                tvOptionC.background = greenBack
                            }
                            3 -> {
                                tvOptionD.isSelected = true
                                tvOptionD.background = greenBack
                            }
                        }
                    } else {
                        if (childOption.user_checked == true) {
                            when (index) {
                                0 -> {
                                    tvOptionA.isSelected = true
                                    tvOptionA.background = greenBack
                                }
                                1 -> {
                                    tvOptionB.isSelected = true
                                    tvOptionB.background = greenBack
                                }
                                2 -> {
                                    tvOptionC.isSelected = true
                                    tvOptionC.background = greenBack
                                }
                                3 -> {
                                    tvOptionD.isSelected = true
                                    tvOptionD.background = greenBack
                                }
                            }
                        } else {
//                            oldQst.childOption.forEachIndexed { index, childOption ->
                            if (oldQst.childOption[index].isSelected) {
                                when (index) {
                                    0 -> {
                                        tvOptionA.isSelected = true
                                        tvOptionA.background = redBack
                                    }
                                    1 -> {
                                        tvOptionB.isSelected = true
                                        tvOptionB.background = redBack
                                    }
                                    2 -> {
                                        tvOptionC.isSelected = true
                                        tvOptionC.background = redBack
                                    }
                                    3 -> {
                                        tvOptionD.isSelected = true
                                        tvOptionD.background = redBack
                                    }
                                }
//                                }
                            } else {
                                tvOptionA.isSelected = false
                                tvOptionA.background = transBack

                                tvOptionB.isSelected = false
                                tvOptionB.background = transBack

                                tvOptionC.isSelected = false
                                tvOptionC.background = transBack

                                tvOptionD.isSelected = false
                                tvOptionD.background = transBack
                            }
                        }
                    }
                }*/

//                newQst.childOption.forEachIndexed { index, childOption ->
//                    if (childOption.user_checked == true){
//                        when (index) {
//                            0 -> {
//                                tvOptionA.isSelected = true
//                                tvOptionA.background = greenBack
//                            }
//                            1 -> {
//                                tvOptionB.isSelected = true
//                                tvOptionB.background = greenBack
//                            }
//                            2 -> {
//                                tvOptionC.isSelected = true
//                                tvOptionC.background = greenBack
//                            }
//                            3 -> {
//                                tvOptionD.isSelected = true
//                                tvOptionD.background = greenBack
//                            }
//                        }
//                    }
//                }

            }
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun addData(
        newQstList: ArrayList<Qst>,
        oldList: ArrayList<Qst>
    ) {
        this.oldList = oldList
        this.newQstList = newQstList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            RvSolutionItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(newQstList[position], oldList[position])
    }

    override fun getItemCount(): Int = newQstList.size
}