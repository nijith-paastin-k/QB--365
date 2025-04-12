package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("user_id") var user_id: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("token") var token: String? = null
)
