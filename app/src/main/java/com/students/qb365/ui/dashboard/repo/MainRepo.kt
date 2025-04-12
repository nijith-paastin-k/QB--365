package com.students.qb365.ui.dashboard.repo

import android.util.Log
import com.students.qb365.api.ApiService
import com.students.qb365.api.Resource
import com.students.qb365.app.QBApp
import com.students.qb365.utils.Constants
import com.students.qb365.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepo @Inject constructor(private val api: ApiService) {

    fun dashboard(token: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.dashboard(token)

        Log.e("HOME_DATA", "$result")
        if (result.status == true && result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun getProfile(board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.getProfile(board)
        Log.e("HOME_DATA", "$result")
        if (result.status == true && result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun editProfile(
        email: String, phone: String, name: String,
        image: String, board: String, school: String, address: String
    ) =
        flow {
            emit(Resource.Loading)
            if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
                emit(Resource.NetworkError(Constants.NETWORK_ERROR))
                return@flow
            }
            val result = api.editProfile(email, phone, name, image, board, school, address)
            Log.e("HOME_DATA", "$result")
            if (result.success == true) {
                emit(Resource.Success(result))

            } else {
                emit(Resource.UnknownError("Something wrong!"))
            }
        }.catch {
            emit(Resource.UnknownError(it.localizedMessage.toString()))
        }.flowOn(Dispatchers.IO)


    fun changePassword(
        old_password: String, new_password: String, confirm_password: String,
        board: String
    ) =
        flow {
            emit(Resource.Loading)
            if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
                emit(Resource.NetworkError(Constants.NETWORK_ERROR))
                return@flow
            }
            val result = api.changePassword(old_password, new_password, confirm_password, board)
            Log.e("HOME_DATA", "$result")
            if (result.success == true) {
                emit(Resource.Success(result))

            } else {
                emit(Resource.UnknownError("Something wrong!"))
            }
        }.catch {
            emit(Resource.UnknownError(it.localizedMessage.toString()))
        }.flowOn(Dispatchers.IO)


    fun contact(
        message: String,
    ) =
        flow {
            emit(Resource.Loading)
            if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
                emit(Resource.NetworkError(Constants.NETWORK_ERROR))
                return@flow
            }
            val result = api.contact(message)
            Log.e("HOME_DATA", "$result")
            if (result.success == true) {
                emit(Resource.Success(result))

            } else {
                emit(Resource.UnknownError("Something wrong!"))
            }
        }.catch {
            emit(Resource.UnknownError(it.localizedMessage.toString()))
        }.flowOn(Dispatchers.IO)


    fun validity(
    ) =
        flow {
            emit(Resource.Loading)
            if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
                emit(Resource.NetworkError(Constants.NETWORK_ERROR))
                return@flow
            }
            val result = api.getValidity()
            Log.e("HOME_DATA", "$result")
            if (result.id?.isNotEmpty()!!) {
                emit(Resource.Success(result))

            } else {
                emit(Resource.UnknownError("Something wrong!"))
            }
        }.catch {
            emit(Resource.UnknownError(it.localizedMessage.toString()))
        }.flowOn(Dispatchers.IO)


    fun testSubjects(board: String) =
        flow {
            emit(Resource.Loading)
            if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
                emit(Resource.NetworkError(Constants.NETWORK_ERROR))
                return@flow
            }
            val result = api.testSubjects(board)
            Log.e("HOME_DATA", "$result")
            if (result.data != null) {
                emit(Resource.Success(result))

            } else {
                emit(Resource.UnknownError("Something wrong!"))
            }
        }.catch {
            emit(Resource.UnknownError(it.localizedMessage.toString()))
        }.flowOn(Dispatchers.IO)


    fun forYou(board: String) =
        flow {
            emit(Resource.Loading)
            if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
                emit(Resource.NetworkError(Constants.NETWORK_ERROR))
                return@flow
            }
            val result = api.forYou(board)
            Log.e("HOME_DATA", "$result")
            if (result.data != null) {
                emit(Resource.Success(result))

            } else {
                emit(Resource.UnknownError("Something wrong!"))
            }
        }.catch {
            emit(Resource.UnknownError(it.localizedMessage.toString()))
        }.flowOn(Dispatchers.IO)


    fun inviteEarn() =
        flow {
            emit(Resource.Loading)
            if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
                emit(Resource.NetworkError(Constants.NETWORK_ERROR))
                return@flow
            }
            val result = api.inviteEarn()
            Log.e("HOME_DATA", "$result")
            if (result.data != null) {
                emit(Resource.Success(result))

            } else {
                emit(Resource.UnknownError("Something wrong!"))
            }
        }.catch {
            emit(Resource.UnknownError(it.localizedMessage.toString()))
        }.flowOn(Dispatchers.IO)


    fun search(search: String) =
        flow {
            emit(Resource.Loading)
            if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
                emit(Resource.NetworkError(Constants.NETWORK_ERROR))
                return@flow
            }
            val result = api.search(search)
            Log.e("HOME_DATA", "$result")
            if (result.data != null) {
                emit(Resource.Success(result))

            } else {
                emit(Resource.UnknownError("Something wrong!"))
            }
        }.catch {
            emit(Resource.UnknownError(it.localizedMessage.toString()))
        }.flowOn(Dispatchers.IO)


    fun order() =
        flow {
            emit(Resource.Loading)
            if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
                emit(Resource.NetworkError(Constants.NETWORK_ERROR))
                return@flow
            }
            val result = api.orderDetails()
            Log.e("HOME_DATA", "$result")
            if (result.data != null) {
                emit(Resource.Success(result))

            } else {
                emit(Resource.UnknownError("Something wrong!"))
            }
        }.catch {
            emit(Resource.UnknownError(it.localizedMessage.toString()))
        }.flowOn(Dispatchers.IO)


}