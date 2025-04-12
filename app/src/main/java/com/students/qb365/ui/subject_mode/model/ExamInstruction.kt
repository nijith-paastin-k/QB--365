package com.students.qb365.ui.subject_mode.model

import com.google.gson.annotations.SerializedName

data class ExamInstruction(
    @SerializedName("data") var data: ExamInstructionData? = ExamInstructionData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("test_id") var testId: String? = null
)


data class ExamInstructionData(

    @SerializedName("test_name") var testName: String? = null,
    @SerializedName("test_total_questions") var testTotalQuestions: String? = null,
    @SerializedName("test_duration") var testDuration: String? = null,
    @SerializedName("test_id") var testId: String? = null

)