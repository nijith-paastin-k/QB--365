package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class SubjectChapter(
    @SerializedName("data") var data: SubjectChapterData? = SubjectChapterData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("qbs_sub_id") var qbsSubId: String? = null,
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null
)


data class Subjects(
    @SerializedName("image") var image: String? = null,
    @SerializedName("qbs_dept_name") var qbsDeptName: String? = null,
    @SerializedName("qbs_sub_name") var qbsSubName: String? = null,
    @SerializedName("chapters") var chapters: String? = null
)


data class Chapters(
    @SerializedName("qbs_chapter_id") var qbsChapterId: String? = null,
    @SerializedName("qbs_sub_id") var qbsSubId: String? = null,
    @SerializedName("qbs_chapter_name") var qbsChapterName: String? = null,
    @SerializedName("image") var image: String? = null
)


data class SubjectChapterData(
    @SerializedName("subjects") var subjects: Subjects? = Subjects(),
    @SerializedName("chapters") var chapters: ArrayList<Chapters> = arrayListOf()
)