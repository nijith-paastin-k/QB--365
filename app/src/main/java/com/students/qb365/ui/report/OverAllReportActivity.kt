package com.students.qb365.ui.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.students.qb365.R
import com.students.qb365.databinding.ActivityOverAllReportBinding
import com.students.qb365.ui.report.model.Graph
import com.students.qb365.ui.report.model.OverAllReport
import com.students.qb365.ui.report.viewmodel.ReportViewModel
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverAllReportActivity : AppCompatActivity() {

    private var binding: ActivityOverAllReportBinding? = null

    private val model: ReportViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverAllReportBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            DialogUtil.showProgressDialog(this@OverAllReportActivity)

            val board = SharedPrefs.getString(this@OverAllReportActivity, SharedPrefs.BOARD_ID)

            model.getOverAllReport(board!!, { overAllReport: OverAllReport ->
                DialogUtil.dismissProgressDialog()

                tvTests.text = overAllReport.test.toString()
                tvCompleted.text = overAllReport.completed.toString()
                tvPending.text = overAllReport.pending.toString()

                val data = overAllReport.graph
                val chart = barChart
                initChart(chart, data)

            }, { s ->
                DialogUtil.dismissProgressDialog()
            })

            btnBack.setOnClickListener {
                onBackPressed()
            }

        }


    }

    private fun initChart(chart: BarChart, graphData: ArrayList<Graph>) {
        val entries: MutableList<BarEntry> = ArrayList()

        graphData?.forEachIndexed { index, graphData ->
            entries.add(BarEntry((index).toFloat(), graphData.percent?.toFloat()!!))
        }

        val set = BarDataSet(entries, "Percentage")

        set.color = getColor(R.color.mainPurple)

        val data = BarData(set)

        data.barWidth = 0.9f // set custom bar width

        chart.data = data
        chart.setFitBars(true) // make the x-axis fit exactly all bars

        chart.legend.typeface = ResourcesCompat.getFont(
            this,
            R.font.poppins_regular
        )
        chart.description.isEnabled = false


        val yAxisLeft = chart.axisLeft
        val xAxis = chart.xAxis
        val yAxisRight = chart.axisRight
        yAxisLeft.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "$value%"
            }
        }

        yAxisRight.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "$value%"
            }
        }

        xAxis.granularity = 1f;
        xAxis.isEnabled = true;
        xAxis.setDrawGridLines(false);

        val xAxisLabel: ArrayList<String> = ArrayList()
        xAxisLabel.add("Test 1")
        xAxisLabel.add("Test 2")
        xAxisLabel.add("Test 3")
        xAxisLabel.add("Test 4")
        xAxisLabel.add("Test 5")

        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)

        xAxis.position = XAxis.XAxisPosition.BOTTOM

        chart.setPinchZoom(false)
        chart.isDoubleTapToZoomEnabled = false
        chart.setDrawBarShadow(false)
        chart.setDrawGridBackground(false)
        chart.animateY(1000)

        chart.invalidate()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}