package com.neolab.mvvm_architecture.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

abstract class BaseFragment<VM : BaseViewModel,
        VB : ViewBinding>(classViewModel: KClass<VM>) : Fragment() {

    protected val viewModel: VM by viewModel(clazz = classViewModel)

    private var _viewBinding: VB? = null
    protected val viewBinding get() = _viewBinding!! // ktlint-disable
    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    protected abstract fun initialize()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = inflateViewBinding(inflater, container)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        onSubscribeObserver()
    }

    /**
     * Fragments outlive their views. Make sure you clean up any references to
     * the binding class instance in the fragment's onDestroyView() method.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    open fun onSubscribeObserver() {
        viewModel.run {
            isLoading.observeSingleEvent(viewLifecycleOwner) {
                // TODO show/hide loading
            }
            exception.observeSingleEvent(viewLifecycleOwner) {
                (activity as? BaseActivity<*, *>)?.handleDefaultApiError(it)
            }
        }
    }
}
