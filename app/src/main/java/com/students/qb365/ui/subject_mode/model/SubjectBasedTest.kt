package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class SubjectBasedTest(
    @SerializedName("data") var data: SubjectBasedTestData? = SubjectBasedTestData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("qbs_sub_id") var qbsSubId: String? = null
)

data class Cps(

    @SerializedName("test_id") var testId: String? = null,
    @SerializedName("test_name") var testName: String? = null

)

data class Testid(

    @SerializedName("qbs_exam_id") var qbsExamId: String? = null

)

data class SubjectBasedTestData(

    @SerializedName("testid") var testid: ArrayList<Testid> = arrayListOf(),
    @SerializedName("cps") var cps: ArrayList<Cps> = arrayListOf()

)