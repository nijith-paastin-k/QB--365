package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("message") var message: String? = null,
    @SerializedName("trans_notes") var transNotes: String? = null,
    @SerializedName("status") var status: Boolean? = null

)
