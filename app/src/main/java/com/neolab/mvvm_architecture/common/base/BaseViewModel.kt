package com.neolab.mvvm_architecture.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neolab.mvvm_architecture.utils.DataResult
import com.neolab.mvvm_architecture.utils.liveData.SingleEvent
import com.neolab.mvvm_architecture.utils.test.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 */
abstract class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<SingleEvent<Boolean>>()
    val exception = MutableLiveData<SingleEvent<Exception>>()

    private var loadingCount = 0

    /**
     * Calls api with view model scope
     */
    protected fun <T> viewModelScope(
        liveData: MutableLiveData<T>,
        isShowLoading: Boolean = true,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null,
        onRequest: suspend CoroutineScope.() -> DataResult<T>
    ) {
        if (isShowLoading) showLoading()
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                when (val asynchronousTasks = onRequest(this)) {
                    is DataResult.Success -> {
                        onSuccess?.invoke(asynchronousTasks.data) ?: kotlin.run {
                            liveData.value = asynchronousTasks.data
                        }
                    }
                    is DataResult.Error -> {
                        onError?.invoke(asynchronousTasks.exception) ?: kotlin.run {
                            exception.value = SingleEvent(asynchronousTasks.exception)
                        }
                    }
                }
                if (isShowLoading) hideLoading()
            }
        }
    }

    protected fun viewModelScope(
        isShowLoading: Boolean = true,
        onSuccess: (() -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null,
        onRequest: suspend CoroutineScope.() -> DataResult<Any>
    ) {
        if (isShowLoading) showLoading()
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                when (val asynchronousTasks = onRequest(this)) {
                    is DataResult.Success -> {
                        onSuccess?.invoke()
                    }
                    is DataResult.Error -> {
                        onError?.invoke(asynchronousTasks.exception) ?: kotlin.run {
                            exception.value = SingleEvent(asynchronousTasks.exception)
                        }
                    }
                }
                if (isShowLoading) hideLoading()
            }
        }
    }

    protected fun showLoading() {
        loadingCount++
        if (isLoading.value?.peekContent() != true) isLoading.value = SingleEvent(true)
    }

    protected fun hideLoading() {
        loadingCount--
        if (loadingCount <= 0) {
            // reset loadingCount
            loadingCount = 0
            isLoading.value = SingleEvent(false)
        }
    }
}
