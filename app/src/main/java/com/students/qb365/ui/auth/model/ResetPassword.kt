package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class ResetPassword(
    @SerializedName("status"  ) var status  : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null
)
