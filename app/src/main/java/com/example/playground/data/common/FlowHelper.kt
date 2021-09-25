package com.example.playground.data.common

import com.example.playground.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

/** extension function for Flow Class to emit loading state before the flow starts */
fun <T> Flow<Result<T>>.onFlowStarts() = onStart { emit(Result.Loading) }.catch { e: Throwable ->
    e.printStackTrace()
    emit(Result.Error(DataSourceException.Unexpected(R.string.error_client_unexpected_message)))
}
