package com.example.playground.utils

import android.content.Context
import com.example.playground.R
import com.example.playground.data.common.DataSourceException
import okhttp3.ResponseBody

fun <E> Collection<E>.isNotNullOrEmpty(): Boolean = !this.isNotNullOrEmpty()

fun DataSourceException.getError(context: Context): String {
    return when (messageResource) {
        is Int -> return context.getString(messageResource)
        is ResponseBody? -> return messageResource!!.string()
        is String -> return messageResource
        else -> context.getString(R.string.error_unexpected_message)
    }
}
