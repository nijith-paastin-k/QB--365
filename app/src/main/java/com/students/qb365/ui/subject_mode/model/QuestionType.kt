package com.students.qb365.ui.subject_mode.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionType(
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("board") var board: String? = null,
    @SerializedName("qbs_sub_id") var qbsSubId: String? = null,
    @SerializedName("qbs_chapter_id") var qbsChapterId: String? = null,
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null

) : Parcelable

@Parcelize
data class Qstarr(

    @SerializedName("name") var name: String? = null,
    @SerializedName("qbs_qs_type_id") var qbsQsTypeId: String? = null

) : Parcelable

@Parcelize
data class Data(
    @SerializedName("qstarr") var qstarr: ArrayList<Qstarr> = arrayListOf()
) : Parcelable