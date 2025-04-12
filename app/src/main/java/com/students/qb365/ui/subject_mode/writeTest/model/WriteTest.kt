package com.students.qb365.ui.subject_mode.writeTest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WriteTest(
    @SerializedName("data") var data: WriteTestData? = WriteTestData(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("test_id") var testId: String? = null
) : Parcelable

@Parcelize
data class Info(

    @SerializedName("test_id") var testId: String? = null,
    @SerializedName("test_name") var testName: String? = null,
    @SerializedName("test_total_questions") var testTotalQuestions: String? = null,
    @SerializedName("test_duration") var testDuration: String? = null

) : Parcelable

@Parcelize
data class ChildOption(

    @SerializedName("range") var range: String? = null,
    @SerializedName("option") var option: String? = null,
    @SerializedName("qbs_qst_id") var qbsQstId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("value") var value: String? = null,
    @SerializedName("anstext") var anstext: String? = null,
    @SerializedName("true_answer") var true_answer: Boolean? = null,
    @SerializedName("wrong_answer") var wrong_answer: Boolean? = null,
    var isSelected : Boolean = false

): Parcelable

@Parcelize
data class Qst(

    @SerializedName("qbs_questions") var qbsQuestions: String? = null,
    @SerializedName("qbs_qst_id") var qbsQstId: String? = null,
    @SerializedName("child_option") var childOption: ArrayList<ChildOption> = arrayListOf(),
    var attempted: Boolean = false,
    var skipped: Boolean = false,
    var revised: Boolean = false,
    var notAttempted: Boolean = false

) : Parcelable

@Parcelize
data class WriteTestData(

    @SerializedName("info") var info: Info? = Info(),
    @SerializedName("qst") var qst: ArrayList<Qst> = arrayListOf()

) : Parcelable