package com.students.qb365.ui.report.model

import com.google.gson.annotations.SerializedName

data class OverAllReport(
    @SerializedName("qbs_dept_name") var qbsDeptName: String? = null,
    @SerializedName("test") var test: Int? = null,
    @SerializedName("completed") var completed: Int? = null,
    @SerializedName("pending") var pending: Int? = null,
    @SerializedName("graph") var graph: ArrayList<Graph> = arrayListOf(),
    @SerializedName("board") var board: String? = null
)


data class Graph(

    @SerializedName("percent") var percent: Double? = null,
    @SerializedName("name") var name: String? = null

)