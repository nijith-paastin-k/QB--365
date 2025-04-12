package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class ChapterBasesTest(
    @SerializedName("data") var data: ChapterBasesTestData? = ChapterBasesTestData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("test_chapter_id") var testChapterId: String? = null

)


data class ChapterBased(

    @SerializedName("test_id") var testId: String? = null,
    @SerializedName("test_name") var testName: String? = null

)

data class ChapterBasesTestData(

    @SerializedName("chapter") var chapter: ArrayList<ChapterBased> = arrayListOf()

)