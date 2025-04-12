package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("data") var data: LoginData? = LoginData(),
    @SerializedName("token") var token: String? = null,
    @SerializedName("board") var board: String? = null,
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null
)

data class LoginData(

    @SerializedName("stud_name") var studName: String? = null,
    @SerializedName("stud_id") var studId: String? = null

)