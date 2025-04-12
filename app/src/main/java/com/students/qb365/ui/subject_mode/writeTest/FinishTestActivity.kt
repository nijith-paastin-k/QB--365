package com.students.qb365.ui.subject_mode.writeTest

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.students.qb365.R
import com.students.qb365.databinding.ActivityTestCompleteBinding
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.ui.subject_mode.writeTest.model.GraphData
import com.students.qb365.ui.subject_mode.writeTest.model.Qst
import com.students.qb365.utils.DialogUtil
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


@AndroidEntryPoint
class FinishTestActivity : AppCompatActivity() {

    private var binding: ActivityTestCompleteBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var boardId = ""
    private var testId = ""
    private var timingSec = ""
    private var qstList = arrayListOf<Qst>()
    private var attempt = ""
    private var skipped = ""
    private var insertedId = ""
    private var notAttempt = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCompleteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        testId = intent.getStringExtra("testId")!!
        boardId = intent.getStringExtra("boardId")!!
        timingSec = intent.getStringExtra("timing")!!
        qstList = intent.getParcelableArrayListExtra("data")!!
        attempt = intent.getStringExtra("attempted")!!
        skipped = intent.getStringExtra("skipped")!!
        notAttempt = intent.getStringExtra("not_attempted")!!

        binding?.apply {

            DialogUtil.showProgressDialog(this@FinishTestActivity)

            val data = HashMap<String, Any>()

            data["board"] = boardId
            data["test_id"] = testId
            data["timing"] = timingSec
            data["board"] = boardId
            data["answers[attempted]"] = attempt
            data["answers[skipped]"] = skipped
            data["answers[bookmarked]"] = "0"
            data["answers[not_attempt]"] = notAttempt

            for (qst in qstList) {
                if (qst.attempted) {
                    for (childOption in qst.childOption) {
                        if (childOption.isSelected) {
                            data[childOption.name.toString()] = childOption.value.toString()
                        }
                    }
                }
            }


            model.finishTest(data, { finishTest ->
                DialogUtil.dismissProgressDialog()

                insertedId = finishTest.data?.insertId.toString()

                val correct = finishTest.data?.correct?.toInt()
                val total = finishTest.data?.chapters?.testTotalQuestions?.toInt()

                tvScore.text = "$correct / $total"

                tvTime.text = finishTest.data?.timeTaken

                val a = correct?.toFloat()?.div(total!!)

                val percentage = a?.times(100)

                progressBar.progress = percentage!!

                tvPercentage.text = percentage.roundToInt().toString() + "%"

                tvCorrectAns.text = correct.toString()
                tvWrongAns.text = finishTest.data?.wrong.toString()
                tvSkippedAns.text = finishTest.data?.skipped.toString()

            }, { s ->
                DialogUtil.dismissProgressDialog()
            })

            btnViewSolution.setOnClickListener {
                val intent = Intent(
                    this@FinishTestActivity,
                    TestSolutionActivity::class.java
                )
                intent.putExtra("boardId", boardId)
                intent.putExtra("testId", testId)
                intent.putExtra("data", qstList)
                intent.putExtra("insertId", insertedId)
                startActivity(intent)
            }


            model.overview(boardId, testId, { overview ->

                val graphData = overview.graphData

                val chart = barChart

                initChart(chart, graphData)

            }, { s ->

            })

            ivBack.setOnClickListener {
                onBackPressed()
            }

        }

    }

    private fun initChart(chart: BarChart, graphData: ArrayList<GraphData>) {
        val entries: MutableList<BarEntry> = ArrayList()

        graphData.forEachIndexed { index, graphData ->
            entries.add(BarEntry((index).toFloat(), graphData.marks?.toFloat()!!))
        }

        val set = BarDataSet(entries, "Percentage")

        set.color = getColor(R.color.mainPurple)

        val data = BarData(set)

        data.barWidth = 0.9f // set custom bar width

        chart.data = data
        chart.setFitBars(true) // make the x-axis fit exactly all bars

        chart.legend.typeface = ResourcesCompat.getFont(
            this@FinishTestActivity,
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

        xAxis.position = XAxisPosition.BOTTOM

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