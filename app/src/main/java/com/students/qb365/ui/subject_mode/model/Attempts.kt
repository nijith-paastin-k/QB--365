package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class Attempts(
    @SerializedName("data") var data: AttemptsData? = AttemptsData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("test_id") var testId: String? = null

)


data class Chapter3(

    @SerializedName("test_name") var testName: String? = null

)

data class Attempt(

    @SerializedName("id") var id: String? = null,
    @SerializedName("Attempt") var Attempt: Int? = null

)

data class Rperc3(

    @SerializedName("rperc") var rperc: Double? = null,
    @SerializedName("name") var name: String? = null

)

data class AttemptsData(

    @SerializedName("chapter") var chapter: Chapter3? = Chapter3(),
    @SerializedName("attempt") var attempt: ArrayList<Attempt> = arrayListOf(),
    @SerializedName("rperc") var rperc: ArrayList<Rperc2> = arrayListOf()

)
