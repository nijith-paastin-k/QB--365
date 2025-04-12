package com.students.qb365.ui.dashboard.model

import com.google.gson.annotations.SerializedName

data class TestSubjects(
    @SerializedName("data") var data: TestSubjectsData? = TestSubjectsData(),
    @SerializedName("board") var board: String? = null
)


data class Subject(

    @SerializedName("qbs_sub_id") var subjectId: String? = null,
    @SerializedName("qbs_sub_image") var subjectImage: String? = null,
    @SerializedName("qbs_sub_name") var subjectName: String? = null,
    @SerializedName("chapters") var chapters: String? = null,
    @SerializedName("test") var test: String? = null

)

data class TestSubjectsData(

    @SerializedName("subject") var subject: ArrayList<Subject> = arrayListOf()

)