package com.students.qb365.api


/**
 * Utility Resource class for API's response
 */

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class NetworkError(val message: String) : Resource<Nothing>()
    data class UnknownError(val message: String) : Resource<Nothing>()
    data class Success<T>(val response: T) : Resource<T>()
}