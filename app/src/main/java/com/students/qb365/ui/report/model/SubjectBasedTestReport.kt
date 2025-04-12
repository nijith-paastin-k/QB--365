package com.students.qb365.ui.report.model

import com.google.gson.annotations.SerializedName

data class SubjectBasedTestReport(
    @SerializedName("qbs_dept_name") var qbsDeptName: String? = null,
    @SerializedName("test") var test: Int? = null,
    @SerializedName("completed") var completed: Int? = null,
    @SerializedName("pending") var pending: Int? = null,
    @SerializedName("graph") var graph: ArrayList<SubjectBasedReportGraph> = arrayListOf(),
    @SerializedName("titlewise") var titlewise: ArrayList<Titlewise> = arrayListOf(),
    @SerializedName("board") var board: String? = null
)

data class SubjectBasedReportGraph(

    @SerializedName("name") var name: String? = null,
    @SerializedName("value") var value: Int? = null

)

data class Titlewise(

    @SerializedName("title") var title: String? = null,
    @SerializedName("subject_id") var subjectId: String? = null,
    @SerializedName("correct") var correct: Int? = null,
    @SerializedName("wrong") var wrong: Int? = null,
    @SerializedName("skipped") var skipped: Int? = null

)