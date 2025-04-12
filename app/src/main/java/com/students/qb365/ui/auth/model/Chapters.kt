package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class Chapters(
    @SerializedName("chapters") var chapters: ChaptersData? = ChaptersData()
)


data class ChaptersDetails(

    @SerializedName("subject_id") var subjectId: String? = null,
    @SerializedName("qbs_chapter_id") var qbsChapterId: String? = null,
    @SerializedName("qbs_chapter_name") var qbsChapterName: String? = null,
    var isSelected: Boolean = false

)


data class ChaptersData(

    @SerializedName("image") var image: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("sub_title") var subTitle: String? = null,
    @SerializedName("details") var details: ArrayList<ChaptersDetails> = arrayListOf()

)