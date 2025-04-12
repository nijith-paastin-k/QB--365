package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class SubjectBasedReport(
    @SerializedName("data") var data: SubjectBasedReportData? = SubjectBasedReportData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("test_chapter_id") var testChapterId: String? = null
)


data class Chapter2(

    @SerializedName("test_id") var testId: String? = null,
    @SerializedName("test_name") var testName: String? = null

)

data class Rperc2(

    @SerializedName("rperc") var rperc: Double? = null,
    @SerializedName("name") var name: String? = null

)

data class SubjectBasedReportData(

    @SerializedName("chapter") var chapter: ArrayList<Chapter2> = arrayListOf(),
    @SerializedName("rperc") var rperc: ArrayList<Rperc2> = arrayListOf()

)