package com.students.qb365.ui.subject_mode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.students.qb365.api.Resource
import com.students.qb365.ui.subject_mode.model.*
import com.students.qb365.ui.subject_mode.practiceQuestion.model.PracticeQuestion
import com.students.qb365.ui.subject_mode.repo.SubjectModeRepo
import com.students.qb365.ui.subject_mode.writeTest.model.FinishTest
import com.students.qb365.ui.subject_mode.writeTest.model.Overview
import com.students.qb365.ui.subject_mode.writeTest.model.WriteTest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SubjectDemoViewModel @Inject constructor(private val repo: SubjectModeRepo) : ViewModel() {


    fun subjectChapter(
        subID: String,
        board: String,
        response: (SubjectChapter) -> Unit,
        error: (String) -> Unit
    ) {

        repo.subjectChapters(subID, board).onEach {
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


    fun questionType(
        subID: String,
        board: String,
        chapterID: String,
        response: (QuestionType) -> Unit,
        error: (String) -> Unit
    ) {

        repo.questionType(subID, board, chapterID).onEach {
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


    fun getWriteTestType(
        subID: String,
        board: String,
        response: (WriteTestType) -> Unit,
        error: (String) -> Unit
    ) {

        repo.writeTestType(subID, board).onEach {
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


    fun getSubjectBasedTest(
        subID: String,
        board: String,
        response: (SubjectBasedTest) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getSubjectBased(subID, board).onEach {
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

    fun getChapterListTest(
        subID: String,
        board: String,
        response: (ChapterListTest) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getChapterBasedList(subID, board).onEach {
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


    fun getChapterBasedTest(
        testId: String,
        board: String,
        response: (ChapterBasesTest) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getChapterBasedTest(testId, board).onEach {
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

    fun getExamInstruction(
        testId: String,
        board: String,
        response: (ExamInstruction) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getExamInstruction(testId, board).onEach {
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


    fun writeTest(
        testId: String,
        board: String,
        response: (WriteTest) -> Unit,
        error: (String) -> Unit
    ) {

        repo.writeTest(testId, board).onEach {
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


    fun finishTest(
        data: HashMap<String, Any>,
        response: (FinishTest) -> Unit,
        error: (String) -> Unit
    ) {

        repo.finishTest(data).onEach {
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


    fun answerKey(
        board: String, testId: String, insertId: String,
        response: (WriteTest) -> Unit,
        error: (String) -> Unit
    ) {

        repo.answerKey(board, testId, insertId).onEach {
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


    fun overview(
        board: String, testId: String,
        response: (Overview) -> Unit,
        error: (String) -> Unit
    ) {

        repo.overview(board, testId).onEach {
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


    fun practiseQuestion(
        qbs_subject_id: String,
        qbs_chapter_id: String,
        qbs_qst_type: String,
        qbs_creative: String,
        board: String,
        response: (PracticeQuestion) -> Unit,
        error: (String) -> Unit
    ) {

        repo.practiceQuestion(
            qbs_subject_id, qbs_chapter_id,
            qbs_qst_type, qbs_creative, board
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


    fun testReport(
        qbs_subject_id: String,
        board: String,
        response: (TestReport) -> Unit,
        error: (String) -> Unit
    ) {

        repo.testReport(
            qbs_subject_id, board
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


    fun subjectBasedReport(
        test_sub_id: String,
        board: String,
        response: (SubjectBasedReport) -> Unit,
        error: (String) -> Unit
    ) {

        repo.subjectBasedReport(
            test_sub_id, board
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


    fun attempts(
        test_sub_id: String,
        board: String,
        response: (Attempts) -> Unit,
        error: (String) -> Unit
    ) {

        repo.attempts(
            test_sub_id, board
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


    fun attemptSingle(
        test_sub_id: String,
        board: String,
        attemptId: String,
        response: (AttemptsSingle) -> Unit,
        error: (String) -> Unit
    ) {

        repo.attemptSingle(
            test_sub_id, board, attemptId
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


    fun reportExamListChapter(
        test_chapter_id: String,
        board: String,
        response: (ReportExamListChapter) -> Unit,
        error: (String) -> Unit
    ) {

        repo.reportExamListChapter(
            test_chapter_id, board
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
}