package com.students.qb365.ui.auth.model


import com.google.gson.annotations.SerializedName


data class Board(
    @SerializedName("board") var board : List<BoardData>
)

data class BoardData (
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String

)