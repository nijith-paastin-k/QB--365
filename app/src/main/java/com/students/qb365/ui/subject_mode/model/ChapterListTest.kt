package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class ChapterListTest(
    @SerializedName("data") var data: ChapterListTestData? = ChapterListTestData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("qbs_sub_id") var qbsSubId: String? = null,
    @SerializedName("status") var status: Boolean? = null
)


data class Chapter(

    @SerializedName("qbs_chapter_id") var qbsChapterId: String? = null,
    @SerializedName("qbs_chapter_name") var qbsChapterName: String? = null,
    @SerializedName("total") var total: String? = null

)

data class ChapterListTestDataInfo(

    @SerializedName("image") var image: String? = null,
    @SerializedName("qbs_dept_name") var qbsDeptName: String? = null,
    @SerializedName("qbs_sub_name") var qbsSubName: String? = null,
    @SerializedName("chapters") var chapters: String? = null

)

data class ChapterListTestData(

    @SerializedName("chapter") var chapter: ArrayList<Chapter> = arrayListOf(),
    @SerializedName("info") var info: ChapterListTestDataInfo? = ChapterListTestDataInfo()

)