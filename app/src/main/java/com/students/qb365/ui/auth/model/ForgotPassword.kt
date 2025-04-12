package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class ForgotPassword(
    @SerializedName("status") var status : Boolean,
    @SerializedName("message") var message : String,
    @SerializedName("otp") var otp : Int
)
