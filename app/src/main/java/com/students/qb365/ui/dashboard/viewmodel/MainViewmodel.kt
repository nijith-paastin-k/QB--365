package com.students.qb365.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.students.qb365.api.Resource
import com.students.qb365.ui.auth.model.Dashboard
import com.students.qb365.ui.dashboard.model.Profile
import com.students.qb365.ui.dashboard.model.Search
import com.students.qb365.ui.dashboard.model.TestSubjects
import com.students.qb365.ui.dashboard.repo.MainRepo
import com.students.qb365.ui.navigationMenu.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MainRepo) : ViewModel() {

    fun dashboard(
        token: String,
        response: (Dashboard) -> Unit,
        error: (String) -> Unit
    ) {

        repo.dashboard(token).onEach {
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


    fun profile(
        board: String,
        response: (Profile) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getProfile(board).onEach {
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


    fun editProfile(
        email: String, phone: String, name: String,
        image: String, board: String, school: String, address: String,
        response: (BaseResponse) -> Unit,
        error: (String) -> Unit
    ) {

        repo.editProfile(
            email, phone, name, image,
            board, school, address
        ).onEach {
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


    fun changePassword(
        old_password: String, new_password: String, confirm_password: String,
        board: String,
        response: (BaseResponse) -> Unit,
        error: (String) -> Unit
    ) {

        repo.changePassword(old_password, new_password, confirm_password, board).onEach {
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


    fun contact(
        message: String,
        response: (BaseResponse2) -> Unit,
        error: (String) -> Unit
    ) {

        repo.contact(message).onEach {
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


    fun validity(
        response: (Validity) -> Unit,
        error: (String) -> Unit
    ) {

        repo.validity().onEach {
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


    fun testSubjects(
        board: String,
        response: (TestSubjects) -> Unit,
        error: (String) -> Unit
    ) {

        repo.testSubjects(board).onEach {
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


    fun forYou(
        board: String,
        response: (ForYou) -> Unit,
        error: (String) -> Unit
    ) {

        repo.forYou(board).onEach {
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


    fun inviteEarn(
        response: (InviteEarn) -> Unit,
        error: (String) -> Unit
    ) {

        repo.inviteEarn().onEach {
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


    fun search(
        search: String,
        response: (Search) -> Unit,
        error: (String) -> Unit
    ) {

        repo.search(search).onEach {
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


    fun order(
        response: (OrderDetail) -> Unit,
        error: (String) -> Unit
    ) {

        repo.order().onEach {
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