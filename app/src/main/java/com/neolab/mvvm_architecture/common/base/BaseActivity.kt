package com.neolab.mvvm_architecture.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.neolab.mvvm_architecture.extension.handleDefaultApiError
import com.neolab.mvvm_architecture.utils.liveData.observeSingleEvent
import kotlin.reflect.KClass
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 *
 * @VM -> view model
 * @classViewModel -> class view model
 * @VB -> view binding
 * @initialize -> init UI, adapter, listener...
 * @onSubscribeObserver -> subscribe observer live data
 *
 */

abstract class BaseActivity<VM : BaseViewModel,
        VB : ViewBinding>(classViewModel: KClass<VM>) : AppCompatActivity() {

    protected val viewModel: VM by viewModel(clazz = classViewModel)
    protected lateinit var viewBinding: VB
    abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    protected abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflateViewBinding(layoutInflater)
        setContentView(viewBinding.root)
        initialize()
        onSubscribeObserver()
    }

    open fun onSubscribeObserver() {
        viewModel.run {
            isLoading.observeSingleEvent(this@BaseActivity) {
                // TODO show/hide loading
            }
            exception.observeSingleEvent(this@BaseActivity) {
                handleDefaultApiError(it)
            }
        }
    }
}
