package com.students.qb365.ui.dashboard.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    @SerializedName("data") var data: ProfileData? = ProfileData(),
    @SerializedName("department") var department: String? = null,
    @SerializedName("status") var status: Boolean? = null
) : Parcelable


@Parcelize
data class ProfileData(

    @SerializedName("stud_id") var studId: String? = null,
    @SerializedName("stud_name") var studName: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("points") var points: String? = null,
    @SerializedName("refered_users") var referedUsers: String? = null,
    @SerializedName("staff_referer") var staffReferer: String? = null,
    @SerializedName("school_name") var schoolName: String? = null,
    @SerializedName("stud_roll_no") var studRollNo: String? = null,
    @SerializedName("stud_standard") var studStandard: String? = null,
    @SerializedName("stud_section") var studSection: String? = null,
    @SerializedName("stud_phone") var studPhone: String? = null,
    @SerializedName("stud_email") var studEmail: String? = null,
    @SerializedName("stud_pwd") var studPwd: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("stud_address") var studAddress: String? = null,
    @SerializedName("stud_board") var studBoard: String? = null,
    @SerializedName("test_bookmarks") var testBookmarks: String? = null,
    @SerializedName("stud_added_on") var studAddedOn: String? = null,
    @SerializedName("stud_modified_on") var studModifiedOn: String? = null,
    @SerializedName("stud_added_by") var studAddedBy: String? = null,
    @SerializedName("stud_modified_by") var studModifiedBy: String? = null,
    @SerializedName("stud_status") var studStatus: String? = null,
    @SerializedName("paid_status") var paidStatus: String? = null,
    @SerializedName("payment_mode") var paymentMode: String? = null,
    @SerializedName("token") var token: String? = null

) : Parcelable
