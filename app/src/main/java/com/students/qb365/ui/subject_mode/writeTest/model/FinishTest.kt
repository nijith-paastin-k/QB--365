package com.students.qb365.ui.subject_mode.writeTest.model

import com.google.gson.annotations.SerializedName

data class FinishTest(
    @SerializedName("data") var data: FinishTestData? = FinishTestData(),
    @SerializedName("board") var board: String? = null
)


data class Chapters(

    @SerializedName("test_name") var testName: String? = null,
    @SerializedName("test_total_questions") var testTotalQuestions: String? = null,
    @SerializedName("test_id") var testId: String? = null

)


data class FinishTestData(

    @SerializedName("test_id") var testId: String? = null,
    @SerializedName("insert_id") var insertId: Int? = null,
    @SerializedName("time_taken") var timeTaken: String? = null,
    @SerializedName("correct") var correct: Int? = null,
    @SerializedName("chapters") var chapters: Chapters? = Chapters(),
    @SerializedName("wrong") var wrong: Int? = null,
    @SerializedName("skipped") var skipped: Int? = null

)