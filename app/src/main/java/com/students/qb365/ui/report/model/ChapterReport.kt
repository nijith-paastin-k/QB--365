package com.students.qb365.ui.report.model

import com.google.gson.annotations.SerializedName

data class ChapterReport(
    @SerializedName("title") var title: String? = null,
    @SerializedName("chapters") var chapters: Int? = null,
    @SerializedName("data") var data: ArrayList<ChapterReportData> = arrayListOf(),
    @SerializedName("board") var board: String? = null
)


data class ChapterReportData(

    @SerializedName("qbs_chapter_id") var qbsChapterId: String? = null,
    @SerializedName("name") var name: String? = null

)