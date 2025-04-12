package com.students.qb365.ui.navigationMenu.model

import com.google.gson.annotations.SerializedName

data class BaseResponse2(
    @SerializedName("status") var success: Boolean? = null,
    @SerializedName("message") var message: String? = null
)
