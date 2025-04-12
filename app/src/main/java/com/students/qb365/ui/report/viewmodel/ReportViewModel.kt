package com.students.qb365.ui.report.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.students.qb365.api.Resource
import com.students.qb365.ui.report.model.*
import com.students.qb365.ui.report.repo.ReportRepo
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
class ReportViewModel @Inject constructor(private val repo: ReportRepo) : ViewModel() {


    fun getOverAllReport(
        board: String,
        response: (OverAllReport) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getOverAllReport(board).onEach {
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


    fun getTestWiseReport(
        board: String,
        response: (TestWiseReport) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getTestWiseReport(board).onEach {
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


    fun getReportSubjectSingle(
        board: String,
        subId: String,
        response: (ReportSubjectSingle) -> Unit,
        error: (String) -> Unit
    ) {

        repo.getReportSubjectsSingle(board, subId).onEach {
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


    fun overAllSubjectBased(
        board: String,
        response: (SubjectBasedTestReport) -> Unit,
        error: (String) -> Unit
    ) {

        repo.overAllSubjectBasedReport(board).onEach {
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


    fun subjectChapterBased(
        board: String,
        response: (SubjectChapterBased) -> Unit,
        error: (String) -> Unit
    ) {

        repo.subjectChapterBased(board).onEach {
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


    fun chaptersReport(
        board: String,
        subId: String,
        response: (ChapterReport) -> Unit,
        error: (String) -> Unit
    ) {

        repo.chapterBased(board, subId).onEach {
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


    fun chapterBasedReport(
        board: String,
        chapId: String,
        response: (ChapterBasedReport) -> Unit,
        error: (String) -> Unit
    ) {

        repo.chapterBasedReport(board, chapId).onEach {
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