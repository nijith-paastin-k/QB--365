package com.students.qb365.ui.navigationMenu.model

import com.google.gson.annotations.SerializedName

data class OrderDetail(
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("message") var message: String? = null
)


data class Data(

    @SerializedName("date") var date: String? = null,
    @SerializedName("board") var board: String? = null,
    @SerializedName("qbs_dept_name") var qbsDeptName: String? = null,
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("payment_mode") var paymentMode: String? = null,
    @SerializedName("description") var description: ArrayList<String> = arrayListOf()

)