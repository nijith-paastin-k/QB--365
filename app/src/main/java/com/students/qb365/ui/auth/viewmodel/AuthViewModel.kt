package com.students.qb365.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.students.qb365.api.Resource
import com.students.qb365.ui.auth.model.*
import com.students.qb365.ui.auth.repo.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: AuthRepo) : ViewModel() {

    fun doLogin(
        email: String,
        password: String,
        response: (Login) -> Unit,
        error: (String) -> Unit
    ) {

        repo.doLogin(email, password).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun register(
        map: HashMap<String, Any>,
        response: (Register) -> Unit,
        error: (String) -> Unit
    ) {

        repo.register(map).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun getState(
        response: (State) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getState().onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun getCity(
        stateId: String,
        response: (City) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getCity(stateId).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun forgotPassword(
        num: String,
        response: (ForgotPassword) -> Unit,
        error: (String) -> Unit
    ) {

        repo.forgotPassword(num).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun getBoards(
        token:String,
        response: (Board) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getBoards(token).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun getClasses(
        boardID: String,
        response: (Classes) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getClasses(boardID).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun getPurchaseType(
        response: (PurchaseType) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getPurchaseType().onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun getSubjects(
        boardID: String,
        packID: String,
        response: (Subjects) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getSubjects(boardID, packID).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun getChapters(
        boardID: String,
        qbs_sub_id: String,
        response: (Chapters) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getChapters(boardID, qbs_sub_id).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun checkPhone(
        phone: String,
        response: (ForgotPassword) -> Unit,
        error: (String) -> Unit
    ) {

        repo.checkPhone(phone).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun resetPassword(
        phone: String,
        password: String,
        rpassword: String,
        response: (ResetPassword) -> Unit,
        error: (String) -> Unit
    ) {

        repo.resetPassword(phone, password, rpassword).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun paymentSuccess(
        auth: String,
        response: (Payment) -> Unit,
        error: (String) -> Unit
    ) {
        repo.paymentSuccess(auth).onEach {
            when (it) {
                Resource.Loading -> {

                }
                is Resource.NetworkError -> {
                    error(it.message)
                }
                is Resource.Success -> {
                    response(it.response!!)
                }
                is Resource.UnknownError -> {
                    error(it.message)
                }
            }
        }.launchIn(viewModelScope)
    }


}