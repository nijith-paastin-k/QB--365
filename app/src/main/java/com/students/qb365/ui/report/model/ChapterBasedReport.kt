package com.students.qb365.ui.report.model

import com.google.gson.annotations.SerializedName

data class ChapterBasedReport(
    @SerializedName("data") var data: ChapterBasedReportData? = ChapterBasedReportData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("test_chapter_id") var testChapterId: String? = null
)


data class Rperc(

    @SerializedName("percent") var percent: String? = null,
    @SerializedName("name") var name: String? = null

)

data class ChapterBasedReportData(

    @SerializedName("image_url") var imageUrl: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("tests") var tests: Int? = null,
    @SerializedName("questions") var questions: String? = null,
    @SerializedName("attempts") var attempts: String? = null,
    @SerializedName("correct") var correct: String? = null,
    @SerializedName("wrong") var wrong: String? = null,
    @SerializedName("skipped") var skipped: String? = null,
    @SerializedName("rperc") var rperc: ArrayList<Rperc> = arrayListOf()

)