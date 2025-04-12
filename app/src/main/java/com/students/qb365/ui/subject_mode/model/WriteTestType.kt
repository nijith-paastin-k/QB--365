package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class WriteTestType(
    @SerializedName("data") var data: WriteTestTypeData? = WriteTestTypeData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("qbs_sub_id") var qbsSubId: String? = null,
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null

)


data class Total(

    @SerializedName("name") var name: String? = null,
    @SerializedName("total") var total: String? = null

)

data class Info(

    @SerializedName("image") var image: String? = null,
    @SerializedName("qbs_dept_name") var qbsDeptName: String? = null,
    @SerializedName("qbs_sub_name") var qbsSubName: String? = null,
    @SerializedName("chapters") var chapters: String? = null

)

data class WriteTestTypeData(

    @SerializedName("total") var total: ArrayList<Total> = arrayListOf(),
    @SerializedName("info") var info: Info? = Info()

)