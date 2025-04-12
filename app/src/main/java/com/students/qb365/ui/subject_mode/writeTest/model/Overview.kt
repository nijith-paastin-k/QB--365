package com.students.qb365.ui.subject_mode.writeTest.model

import com.google.gson.annotations.SerializedName

data class Overview(
    @SerializedName("graph_data") var graphData: ArrayList<GraphData> = arrayListOf(),
    @SerializedName("data") var data: OverviewData? = OverviewData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("test_id") var testId: String? = null
)


data class GraphData(

    @SerializedName("title") var title: String? = null,
    @SerializedName("marks") var marks: String? = null

)

data class Chapter(

    @SerializedName("attempts") var attempts: Int? = null,
    @SerializedName("questions") var questions: String? = null,
    @SerializedName("total_time") var totalTime: String? = null,
    @SerializedName("time_take") var timeTake: String? = null,
    @SerializedName("correct") var correct: String? = null,
    @SerializedName("wrong") var wrong: Int? = null,
    @SerializedName("skipped") var skipped: Int? = null,
    @SerializedName("test_name") var testName: String? = null,
    @SerializedName("test_id") var testId: String? = null

)

data class OverviewData(

    @SerializedName("chapter") var chapter: Chapter? = Chapter(),
    @SerializedName("per") var per: Int? = null

)