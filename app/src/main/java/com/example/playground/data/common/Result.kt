package com.example.playground.data.common

sealed class Result<out T> {
    data class Success<out T>(val response: T) : Result<T>()
    data class Error(val exception: DataSourceException) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(response)
    return this
}

inline fun <T : Any> Result<T>.onError(action: (DataSourceException) -> Unit): Result<T> {
    if (this is Result.Error) action(exception)
    return this
}

inline fun <T : Any> Result<T>.onLoading(action: () -> Unit): Result<T> {
    if (this is Result.Loading) action()
    return this
}
