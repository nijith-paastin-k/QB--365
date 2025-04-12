package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class Dashboard(
    @SerializedName("data") var data: DashboardData? = DashboardData(),
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null
)

data class DashboardDetails(

    @SerializedName("qbs_sub_id") var qbsSubId: String? = null,
    @SerializedName("board_type") var boardType: Int? = null,
    @SerializedName("qbs_sub_name") var qbsSubName: String? = null,
    @SerializedName("image") var image: String? = null

)

data class DashboardBoard(

    @SerializedName("title") var title: String? = null,
    @SerializedName("details") var details: ArrayList<DashboardDetails> = arrayListOf()

)

data class TestPack(

    @SerializedName("title") var title: String? = null,
    @SerializedName("board_type") var boardType: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("image") var image: String? = null

)

data class UpcomingTest(

    @SerializedName("test_id") var testId: String? = null,
    @SerializedName("board_type") var boardType: Int? = null,
    @SerializedName("test_name") var testName: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("date") var date: String? = null

)


data class DashboardData(

    @SerializedName("board") var board: ArrayList<DashboardBoard> = arrayListOf(),
    @SerializedName("test_pack") var testPack: ArrayList<TestPack> = arrayListOf(),
    @SerializedName("upcoming_test") var upcomingTest: ArrayList<ArrayList<UpcomingTest>> = arrayListOf()

)