package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class PurchaseType(
    @SerializedName("type") var type: ArrayList<Type> = arrayListOf()
)


data class Type(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null

)