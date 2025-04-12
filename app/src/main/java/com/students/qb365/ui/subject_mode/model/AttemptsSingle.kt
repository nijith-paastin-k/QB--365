package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class AttemptsSingle(

    @SerializedName("data") var data: AttemptsSingleData? = AttemptsSingleData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("test_id") var testId: String? = null,
    @SerializedName("attempt_id") var attemptId: String? = null
)


data class AttemptsSingleData(

    @SerializedName("test_name") var testName: String? = null,
    @SerializedName("your_score") var yourScore: String? = null,
    @SerializedName("time_taken") var timeTaken: String? = null,
    @SerializedName("attempted_on") var attemptedOn: String? = null,
    @SerializedName("correct") var correct: String? = null,
    @SerializedName("wrong") var wrong: Int? = null,
    @SerializedName("skipped") var skipped: String? = null

)