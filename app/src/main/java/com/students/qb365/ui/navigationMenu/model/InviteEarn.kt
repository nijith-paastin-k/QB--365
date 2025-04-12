package com.students.qb365.ui.navigationMenu.model

import com.google.gson.annotations.SerializedName

data class InviteEarn(

    @SerializedName("data") var data: InviteEarnData? = InviteEarnData(),
    @SerializedName("status") var status: Boolean? = null
)


data class Info(

    @SerializedName("points") var points: String? = null,
    @SerializedName("code") var code: String? = null

)

data class InviteEarnData(

    @SerializedName("info") var info: Info? = Info()

)