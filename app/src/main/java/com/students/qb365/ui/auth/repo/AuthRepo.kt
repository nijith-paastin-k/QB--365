package com.students.qb365.ui.auth.repo

import android.util.Log
import com.students.qb365.api.ApiService
import com.students.qb365.api.Resource
import com.students.qb365.app.QBApp
import com.students.qb365.utils.Constants.NETWORK_ERROR
import com.students.qb365.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepo @Inject constructor(private val api: ApiService) {

    /**
     * Login
     */
    fun doLogin(email: String, password: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.login(email, password)
        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Register
     */
    fun register(map: HashMap<String, Any>) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.register(map)
        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Get State
     */
    fun getState() = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.getState()
        Log.e("HOME_DATA", "$result")
        if (result.state.size > 0 && result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Get City
     */
    fun getCity(stateId: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.getCity(stateId)
        Log.e("HOME_DATA", "$result")
        if (result.city.size > 0 && result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Forgot passsword
     */
    fun forgotPassword(number: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.forgotPassword(number)
        Log.e("HOME_DATA", "$result")
        if (result.status && result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Get Boards
     */
    fun getBoards(token: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.getBoards(token)
        Log.e("HOME_DATA", "$result")
        if (result.board != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Get Classes as per boards
     */
    fun getClasses(boardId: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.getClass(boardId)
        Log.e("HOME_DATA", "$result")
        if (result.classes != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Get Purchase type
     */
    fun getPurchaseType() = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.getPurchaseType()
        Log.e("HOME_DATA", "$result")
        if (result.type != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Get Subjects
     */
    fun getSubjects(boardID: String, packID: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.getSubjects(boardID, packID)
        Log.e("HOME_DATA", "$result")
        if (result.subjects != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Get Chapters
     */
    fun getChapters(boardID: String, qbs_sub_id: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.getChapters(boardID, qbs_sub_id)

        Log.e("HOME_DATA", "$result")
        if (result.chapters != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Check Phone
     */
    fun checkPhone(phone: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }
        val result = api.checkPhone(phone)

        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Check Phone
     */
    fun resetPassword(phone: String, password: String, rPassword: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }

        val result = api.resetPassword(phone, password, rPassword)

        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    /**
     * Check Phone
     */
    fun paymentSuccess(auth: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(NETWORK_ERROR))
            return@flow
        }

        val result = api.paymentSuccess(auth)

        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)

}