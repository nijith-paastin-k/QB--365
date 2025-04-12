package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class Subjects(
    @SerializedName("subjects") var subjects: ArrayList<SubjectsData> = arrayListOf()
)


data class SubjectsDetails(

    @SerializedName("qbs_sub_id") var qbsSubId: String? = null,
    @SerializedName("qbs_sub_name") var qbsSubName: String? = null,
    @SerializedName("medium") var medium: String? = null,
    @SerializedName("image") var image: String? = null,
    var isSelected: Boolean = false

)


data class SubjectsData(

    @SerializedName("title") var title: String? = null,
    @SerializedName("details") var details: ArrayList<SubjectsDetails> = arrayListOf()

)