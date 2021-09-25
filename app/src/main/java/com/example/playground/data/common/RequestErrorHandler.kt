package com.example.playground.data.common

import com.example.playground.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object RequestErrorHandler {

    private const val HTTP_CODE_CLIENT_START = 400
    private const val HTTP_CODE_CLIENT_END = 499
    private const val HTTP_CODE_SERVER_START = 500
    private const val HTTP_CODE_SERVER_END = 599

    fun getRequestError(throwable: Throwable): DataSourceException {
        return when (throwable) {
            is HttpException -> {
                handleHttpException(throwable)
            }
            is IOException -> {
                DataSourceException.Connection(R.string.error_network)
            }
            is SocketTimeoutException -> {
                DataSourceException.Timeout(R.string.error_timeout_message)
            }
            else -> {
                DataSourceException.Unexpected(R.string.error_unexpected_message)
            }
        }
    }

    private fun handleHttpException(httpException: HttpException): DataSourceException {
        return when (httpException.code()) {
            in HTTP_CODE_CLIENT_START..HTTP_CODE_CLIENT_END -> {
                DataSourceException.Client(R.string.error_client_unexpected_message)
            }
            in HTTP_CODE_SERVER_START..HTTP_CODE_SERVER_END -> {
                DataSourceException.Server(R.string.error_server_unexpected_message)
            }
            else -> {
                DataSourceException.Unexpected(R.string.error_unexpected_message)
            }
        }
    }
}
