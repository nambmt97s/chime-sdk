package com.neolab.mvvm_architecture.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import com.neolab.mvvm_architecture.R
import com.neolab.mvvm_architecture.common.base.BaseActivity
import com.neolab.mvvm_architecture.model.exception.NoConnectivityException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/6/20.
 */

fun FragmentActivity?.hideKeyboard() {
    this?.let {
        val inputManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val v = this.currentFocus ?: return
        inputManager.hideSoftInputFromWindow(
            v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun BaseActivity<*, *>?.handleDefaultApiError(apiError: Exception) {
    this?.let {
        when (apiError) {
            is HttpException -> {
                getErrorMessage(apiError)?.let {
                    // TODO show error message
                }
            }
            is SocketTimeoutException -> {
                // TODO show error message
            }
            is NoConnectivityException -> {
                // TODO show error message
            }
            is IOException -> {
                // TODO show error message
            }
            else -> {
                // TODO show error message
            }
        }
    }
}

fun BaseActivity<*, *>.getErrorMessage(e: Exception): String? {
    val responseBody = (e as HttpException).response()?.errorBody()
    val errorCode = e.response()?.code()
    if (errorCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
        // TODO reLogin
    }

    return responseBody?.let {
        try {
            // Handle get message error when request api, depend on format json api
            val jsonObject = JSONObject(responseBody.string())
            val message = jsonObject.getString("message")
            if (!message.isNullOrBlank()) {
                message
            } else {
                getString(R.string.msg_error_data_parse)
            }
        } catch (ex: Exception) {
            e.message
        }
    } ?: kotlin.run {
        getString(R.string.msg_error_data_parse)
    }
}
