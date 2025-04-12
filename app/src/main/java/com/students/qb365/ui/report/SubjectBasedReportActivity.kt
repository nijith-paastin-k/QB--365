package com.students.qb365.ui.report

import android.R.attr.label
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.students.qb365.R
import com.students.qb365.databinding.ActivitySubjectBasedReportBinding
import com.students.qb365.databinding.RvProgressStatisticsLayoutBinding
import com.students.qb365.ui.report.viewmodel.ReportViewModel
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SubjectBasedReportActivity : AppCompatActivity() {

    private var binding: ActivitySubjectBasedReportBinding? = null

    private val model: ReportViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectBasedReportBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            DialogUtil.showProgressDialog(this@SubjectBasedReportActivity)

            val boardId =
                SharedPrefs.getString(this@SubjectBasedReportActivity, SharedPrefs.BOARD_ID)

            model.overAllSubjectBased(boardId!!, { subjectBasedTestReport ->
                DialogUtil.dismissProgressDialog()

                val pieEntries: ArrayList<PieEntry> = ArrayList()

                val typeAmountMap: MutableMap<String, Int> = HashMap()

                for (graph in subjectBasedTestReport.graph) {
                    typeAmountMap[graph.name!!] = graph.value!!
                }


                val colors: ArrayList<Int> = ArrayList()
                colors.add(Color.parseColor("#304567"))
                colors.add(Color.parseColor("#309967"))
                colors.add(Color.parseColor("#476567"))
                colors.add(Color.parseColor("#890567"))
                colors.add(Color.parseColor("#a35567"))
                colors.add(Color.parseColor("#ff5f67"))
                colors.add(Color.parseColor("#3ca567"))

                for (type in typeAmountMap.keys) {
                    pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))
                }

                val pieDataSet = PieDataSet(pieEntries, "Subjects")

                pieDataSet.valueTextSize = 12f
                pieDataSet.colors = colors
                val pieData = PieData(pieDataSet)
                pieData.setDrawValues(true)

                attemptChart.data = pieData
                attemptChart.invalidate()

                for (data in subjectBasedTestReport.titlewise) {
                    val binding =
                        RvProgressStatisticsLayoutBinding.inflate(layoutInflater, null, false)

                    binding.apply {
                        tvMathsStatsLabel.text = data.title

                        mathsCorrectProgress.progress = data.correct?.toFloat()!!
                        mathsWrongProgress.progress = data.wrong?.toFloat()!!
                        mathsSkippedProgress.progress = data.skipped?.toFloat()!!

                        mathsTvPercentage.text = data.correct.toString() + "%"
                        mathsTvWrongPercentage.text = data.wrong.toString() + "%"
                        mathsTvSkippedPercentage.text = data.skipped.toString() + "%"
                    }

                    llStats.addView(binding.root)
                }


            }, { s ->
                DialogUtil.dismissProgressDialog()

            })

            btnBack.setOnClickListener {
                onBackPressed()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}