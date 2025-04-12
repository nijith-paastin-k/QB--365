package com.students.qb365.ui.subject_mode.testReport

import android.content.Intent
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
import com.students.qb365.databinding.ActivityTestReportBinding
import com.students.qb365.ui.dashboard.model.Subject
import com.students.qb365.ui.subject_mode.ChapterListTestActivity
import com.students.qb365.ui.subject_mode.model.Rperc
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.utils.DialogUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestReportActivity : AppCompatActivity() {

    private var binding: ActivityTestReportBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var subId = ""
    private var boardId = ""
    private var subName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestReportBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        subId = intent.getStringExtra("sub_id")!!
        subName = intent.getStringExtra("sub_name")!!
        boardId = intent.getStringExtra("boardId")!!

        binding?.apply {

            ivBack.setOnClickListener { onBackPressed() }
            DialogUtil.showProgressDialog(this@TestReportActivity)

            model.testReport(subId, boardId, { testReport ->
                DialogUtil.dismissProgressDialog()

                val data = testReport.data

                tvlebel1.text = "${data?.info?.qbsDeptName}  ${data?.info?.qbsSubName} Statistics"

                tvTest.text = data?.tests.toString()
                tvCompleted.text = data?.completedTest.toString()
                tvPending.text = data?.pendingTest.toString()

                val chart = barChart

                initChart(chart, data?.rperc)

                val correct = data?.correct?.removeSuffix("%")?.toFloat()
                val wrong = data?.wrong?.removeSuffix("%")?.toFloat()
                val skipped = data?.skipped?.removeSuffix("%")?.toFloat()

                tvPercentage.text = correct?.toString() + "%"
                tvWrongPercentage.text = wrong?.toString() + "%"
                tvSkippedPercentage.text = skipped?.toString() + "%"

                correctProgress.progress = correct!!
                wrongProgress.progress = wrong!!
                skippedProgress.progress = skipped!!


            }, { s ->
                DialogUtil.dismissProgressDialog()

            })

            ivBack.setOnClickListener {
                onBackPressed()
            }

            cardSubject.setOnClickListener {
                val intent =
                    Intent(
                        this@TestReportActivity,
                        SubjectBasedTestReportActivity::class.java
                    )
                intent.putExtra("sub_id", subId)
                startActivity(intent)
            }

            cardChapter.setOnClickListener {
                val intent = Intent(this@TestReportActivity,
                    ChapterListTestActivity::class.java)
                intent.putExtra("sub_id", subId)
                intent.putExtra("boardId", boardId)
                intent.putExtra("type", "test_report")
                startActivity(intent)
            }

        }


    }

    private fun initChart(chart: BarChart, graphData: ArrayList<Rperc>?) {
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