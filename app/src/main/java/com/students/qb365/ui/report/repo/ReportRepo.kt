package com.students.qb365.ui.report.repo

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
import retrofit2.http.Field
import javax.inject.Inject

class ReportRepo @Inject constructor(private val api: ApiService) {

    fun getOverAllReport(board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.overAllReport(board)
        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun getTestWiseReport(board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.testWiseReport(board)
        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun getReportSubjectsSingle(board: String, subId: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.reportSubjectsSingle(board, subId)
        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun overAllSubjectBasedReport(board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.overAllReportSubjectBased(board)
        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun subjectChapterBased(board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.subjectChapterReport(board)
        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun chapterBased(board: String, subId: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.chapterReport(board, subId)
        Log.e("HOME_DATA", "$result")
        if (result != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun chapterBasedReport(board: String, chapId: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.chapterBasedReport(board, chapId)
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
