package com.students.qb365.ui.subject_mode.repo

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

class SubjectModeRepo @Inject constructor(private val api: ApiService) {

    fun subjectChapters(subID: String, board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.getSubjectChapter(subID, board)
        Log.e("HOME_DATA", "$result")
        if (result.status == true && result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun questionType(subID: String, board: String, chapterId: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.getQuestionType(subID, chapterId, board)
        Log.e("HOME_DATA", "$result")
        if (result.status == true && result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun writeTestType(subID: String, board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.getWriteTestType(subID, board)
        Log.e("HOME_DATA", "$result")
        if (result.status == true && result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun getSubjectBased(subID: String, board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.getSubjectBasedTest(subID, board)
        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun getChapterBasedList(subID: String, board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.getChapterListTest(subID, board)
        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun getChapterBasedTest(testId: String, board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.getChapterBasedTestList(testId, board)
        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun getExamInstruction(testId: String, board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.getExamInstruction(testId, board)
        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun writeTest(testId: String, board: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.writeTest(board, testId)
        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun finishTest(data: HashMap<String, Any>) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.finishTest(data)
        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun answerKey(board: String, testId: String, insertId: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.answerKey(board, testId, insertId)
        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun overview(board: String, testId: String) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result = api.overview(board, testId)
        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun practiceQuestion(
        qbs_subject_id: String,
        qbs_chapter_id: String,
        qbs_qst_type: String,
        qbs_creative: String,
        board: String,
    ) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result =
            api.practiceQuestions(
                qbs_subject_id, qbs_chapter_id,
                qbs_qst_type, qbs_creative, board
            )

        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun testReport(
        qbs_subject_id: String,
        board: String,
    ) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result =
            api.testReport(
                qbs_subject_id, board
            )

        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun subjectBasedReport(
        test_sub_id: String,
        board: String,
    ) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result =
            api.subjectBasedReport(
                test_sub_id, board
            )

        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun attempts(
        test_sub_id: String,
        board: String,
    ) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result =
            api.getAttempts(
                test_sub_id, board
            )

        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun attemptSingle(
        test_sub_id: String,
        board: String,
        attemptId: String,
    ) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result =
            api.getAttemptSingle(
                test_sub_id, board, attemptId
            )

        Log.e("HOME_DATA", "$result")
        if (result.data != null) {
            emit(Resource.Success(result))

        } else {
            emit(Resource.UnknownError("Something wrong!"))
        }
    }.catch {
        emit(Resource.UnknownError(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)


    fun reportExamListChapter(
        test_chapter_id: String,
        board: String,
    ) = flow {
        emit(Resource.Loading)
        if (!Utils.isInternetAvailable(QBApp.instance?.applicationContext!!)) {
            emit(Resource.NetworkError(Constants.NETWORK_ERROR))
            return@flow
        }
        val result =
            api.reportExamListChapter(
                test_chapter_id, board,
            )

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
