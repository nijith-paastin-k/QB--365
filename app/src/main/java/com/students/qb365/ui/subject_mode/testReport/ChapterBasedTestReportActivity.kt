package com.students.qb365.ui.subject_mode.testReport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import com.students.qb365.databinding.ActivityChapterBasedTestReportBinding
import com.students.qb365.ui.subject_mode.model.Attempt
import com.students.qb365.ui.subject_mode.model.Cps
import com.students.qb365.ui.subject_mode.model.Rperc2
import com.students.qb365.ui.subject_mode.viewmodel.SubjectDemoViewModel
import com.students.qb365.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class ChapterBasedTestReportActivity : AppCompatActivity() {

    private var binding: ActivityChapterBasedTestReportBinding? = null

    private val model: SubjectDemoViewModel by viewModels()

    private var chapId = ""
    private var boardId = ""
    private val testSpnList = ArrayList<String>()
    private val attemptsSpnList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBasedTestReportBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        chapId = intent.getStringExtra("chapId")!!

        boardId = SharedPrefs.getString(
            this,
            SharedPrefs.BOARD_ID
        )!!

        binding?.apply {

            ivBack.setOnClickListener { onBackPressed() }

            DialogUtil.showProgressDialog(this@ChapterBasedTestReportActivity)

            model.reportExamListChapter(chapId, boardId, { reportExamListChapter ->
                DialogUtil.dismissProgressDialog()

                val data = reportExamListChapter.data

                subjectTestSpinner(data?.cps)


            }, { s ->
                DialogUtil.dismissProgressDialog()
            })


        }


    }

    private fun subjectTestSpinner(chapter: ArrayList<Cps>?) {

        testSpnList.clear()

        chapter?.forEachIndexed { index, qst ->
            testSpnList.add(qst.testName!!)
        }

        val adapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_item, testSpnList
        )
        adapter.setDropDownViewResource(
            R.layout
                .simple_spinner_dropdown_item
        )

        binding?.spnGoToAns?.adapter = adapter
        binding?.spnGoToAns?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //Attempts api

                    model.attempts(chapter!![position].testId!!, boardId, { attempts ->

                        attemptsSpinner(chapter[position].testId!!, attempts.data?.attempt)

                        val chart = binding?.attemptChart
                        initChart(chart!!, attempts.data?.rperc)

                    }, { s ->

                    })
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    private fun attemptsSpinner(id2: String, chapter: ArrayList<Attempt>?) {

        if (chapter?.size!! <= 0) {
            errorToast("No test were written yet")
            binding?.cardData?.gone()
            binding?.attLabel?.gone()
            binding?.attemRl?.gone()
            binding?.cardGraph?.gone()
        } else {
            binding?.cardData?.show()
            binding?.attLabel?.show()
            binding?.attemRl?.show()
            binding?.cardGraph?.show()
        }

        attemptsSpnList.clear()

        chapter?.forEachIndexed { index, qst ->
            attemptsSpnList.add(qst.Attempt.toString())
        }

        val adapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_item, attemptsSpnList
        )
        adapter.setDropDownViewResource(
            R.layout
                .simple_spinner_dropdown_item
        )

        binding?.spnAttempt?.adapter = adapter
        binding?.spnAttempt?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //attemps single

                    model.attemptSingle(id2,
                        boardId,
                        chapter!![position].id!!, { attemptsSingle ->

                            val data = attemptsSingle.data

                            binding?.testName?.text = data?.testName

                            val correct = attemptsSingle.data?.correct?.toInt()
                            val wrong = attemptsSingle.data?.wrong.toString().toInt()
                            val skipped = attemptsSingle.data?.skipped.toString().toInt()
                            val total = correct!! + wrong + skipped

                            binding?.tvScore?.text = "${data?.yourScore} / $total"

                            binding?.tvTime?.text = attemptsSingle.data?.timeTaken

                            val a = correct.toFloat().div(total)

                            val percentage = a.times(100)

                            binding?.progressBar?.progress = percentage

                            binding?.tvPercentage?.text = percentage.roundToInt().toString() + "%"

                            binding?.tvCorrectAns?.text = correct.toString()
                            binding?.tvWrongAns?.text = wrong.toString()
                            binding?.tvSkippedAns?.text = skipped.toString()

                        }, { s ->
                            Log.e("fddd", s)
                        })


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    private fun initChart(chart: BarChart, graphData: ArrayList<Rperc2>?) {
        val entries: MutableList<BarEntry> = ArrayList()

        graphData?.forEachIndexed { index, graphData ->
            entries.add(BarEntry((index).toFloat(), graphData.rperc?.toFloat()!!))
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