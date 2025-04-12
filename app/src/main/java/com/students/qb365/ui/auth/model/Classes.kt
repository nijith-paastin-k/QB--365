package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class Classes(
    @SerializedName("classes") var classes: List<ClassesData>
)


data class Details(

    @SerializedName("pack_id") var packId: String,
    @SerializedName("pack_name") var packName: String

)


data class ClassesData(

    @SerializedName("title") var title: String,
    @SerializedName("details") var details: List<Details>

)