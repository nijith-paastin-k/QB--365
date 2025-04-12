package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class TestReport(
    @SerializedName("data") var data: TestReportData? = TestReportData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("test_chapter_id") var testChapterId: String? = null

)


data class TestReportInfo(

    @SerializedName("image") var image: String? = null,
    @SerializedName("qbs_dept_name") var qbsDeptName: String? = null,
    @SerializedName("qbs_sub_name") var qbsSubName: String? = null

)

data class Rperc(

    @SerializedName("percent") var percent: Double? = null,
    @SerializedName("name") var name: String? = null

)

data class TestReportData(

    @SerializedName("info") var info: TestReportInfo? = TestReportInfo(),
    @SerializedName("tests") var tests: Int? = null,
    @SerializedName("completed_test") var completedTest: Int? = null,
    @SerializedName("pending_test") var pendingTest: Int? = null,
    @SerializedName("correct") var correct: String? = null,
    @SerializedName("wrong") var wrong: String? = null,
    @SerializedName("skipped") var skipped: String? = null,
    @SerializedName("rperc") var rperc: ArrayList<Rperc> = arrayListOf()

)

