package com.students.qb365.ui.auth.model

data class SignUp(
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var password: String? = null,
    var refCodeStd: String? = null,
    var refCodeStaff: String? = null,
    var schoolName: String? = null,
    var address: String? = null,
    var stateID: String? = null,
    var cityId: String? = null,
)
