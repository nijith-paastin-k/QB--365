package com.students.qb365.ui.report.model

import com.google.gson.annotations.SerializedName

data class SubjectChapterBased(
    @SerializedName("data") var data: ArrayList<SubjectChapterBasedData> = arrayListOf(),
    @SerializedName("board") var board: String? = null

)

data class SubjectChapterBasedData(

    @SerializedName("qbs_sub_id") var qbsSubId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("chapters") var chapters: String? = null

)