package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class ReportExamListChapter(

    @SerializedName("data") var data: ReportExamListChapterData? = ReportExamListChapterData(),
    @SerializedName("board") var board: String? = null
)

data class Cps2(

    @SerializedName("test_id") var testId: String? = null,
    @SerializedName("test_name") var testName: String? = null

)

data class ReportExamListChapterData(

    @SerializedName("cps") var cps: ArrayList<Cps> = arrayListOf()

)