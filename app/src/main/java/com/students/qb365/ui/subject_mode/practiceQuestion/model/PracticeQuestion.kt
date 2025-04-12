package com.students.qb365.ui.subject_mode.practiceQuestion.model

import com.google.gson.annotations.SerializedName

data class PracticeQuestion(
    @SerializedName("data") var data: ArrayList<PracticeQuestionData> = arrayListOf(),
    @SerializedName("board") var board: String? = null
)


data class OptionInDetails(

    @SerializedName("range") var range: String? = null,
    @SerializedName("value") var value: String? = null,
    @SerializedName("result") var result: String? = null

)

data class PracticeQuestionData(

    @SerializedName("question") var question: String? = null,
    @SerializedName("question_type") var questionType: String? = null,
    @SerializedName("option_in_details") var optionInDetails: ArrayList<OptionInDetails> = arrayListOf(),
    @SerializedName("answer") var answer: String? = null,
    @SerializedName("solution") var solution: String? = null

)