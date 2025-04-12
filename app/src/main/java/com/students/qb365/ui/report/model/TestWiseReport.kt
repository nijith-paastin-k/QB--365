package com.students.qb365.ui.report.model

import com.google.gson.annotations.SerializedName

data class TestWiseReport(
    @SerializedName("data") var data: TestWiseReportData? = TestWiseReportData(),
    @SerializedName("board") var board: String? = null
)


data class Subject(

    @SerializedName("qbs_sub_id") var qbsSubId: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("qbs_sub_name") var qbsSubName: String? = null,
    @SerializedName("chapters") var chapters: String? = null,
    @SerializedName("test") var test: String? = null,
    @SerializedName("perc") var perc: Int? = null

)

data class TestWiseReportData(

    @SerializedName("subject") var subject: ArrayList<Subject> = arrayListOf()

)