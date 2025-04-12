package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("state" ) var state : ArrayList<StateData> = arrayListOf()
)


data class StateData (

    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("name" ) var name : String? = null

)