package com.students.qb365.ui.dashboard.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("data") var data: ArrayList<SearchData> = arrayListOf(),
    @SerializedName("message") var message: String? = null
)

data class SearchData(

    @SerializedName("sno") var sno: Int? = null,
    @SerializedName("question") var question: String? = null,
    @SerializedName("solution") var solution: String? = null

)