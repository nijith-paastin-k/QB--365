package com.students.qb365.ui.navigationMenu.model

import com.google.gson.annotations.SerializedName

data class ForYou(
    @SerializedName("data") var data: ForYouData? = ForYouData(),
    @SerializedName("board") var board: String? = null
)


data class Test(

    @SerializedName("test_id") var testId: String? = null,
    @SerializedName("test_name") var testName: String? = null

)


data class ForYouData(

    @SerializedName("test_sub_id") var testSubId: String? = null,
    @SerializedName("test") var test: ArrayList<Test> = arrayListOf()

)