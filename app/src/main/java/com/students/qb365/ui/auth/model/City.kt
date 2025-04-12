package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("city") var city: ArrayList<CityData> = arrayListOf()
)


data class CityData(

    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null

)