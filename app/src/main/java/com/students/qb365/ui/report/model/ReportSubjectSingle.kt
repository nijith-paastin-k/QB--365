package com.students.qb365.ui.report.model

import com.google.gson.annotations.SerializedName

data class ReportSubjectSingle(

    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("qbs_sub_id") var qbsSubId: String? = null
)

data class Subject2(

    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("chapters_total") var chaptersTotal: Int? = null

)

data class TestTotal(

    @SerializedName("total") var total: String? = null,
    @SerializedName("title") var title: String? = null

)

data class Data(

    @SerializedName("subject") var subject: Subject2? = Subject2(),
    @SerializedName("test_total") var testTotal: ArrayList<TestTotal> = arrayListOf()

)