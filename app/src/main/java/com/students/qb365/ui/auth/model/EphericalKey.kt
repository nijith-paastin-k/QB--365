package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class EphericalKey(
    @SerializedName("id") var id: String? = null,
    @SerializedName("object") var object_: String? = null,
    @SerializedName("associated_objects") var associatedObjects: ArrayList<AssociatedObjects> = arrayListOf(),
    @SerializedName("created") var created: Int? = null,
    @SerializedName("expires") var expires: Int? = null,
    @SerializedName("livemode") var livemode: Boolean? = null,
    @SerializedName("secret") var secret: String? = null
)

data class AssociatedObjects(

    @SerializedName("id") var id: String? = null,
    @SerializedName("type") var type: String? = null

)
