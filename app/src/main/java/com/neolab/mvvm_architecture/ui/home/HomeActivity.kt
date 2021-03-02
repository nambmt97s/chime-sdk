package com.neolab.mvvm_architecture.ui.home

import android.view.LayoutInflater
import com.neolab.mvvm_architecture.common.base.BaseActivity
import com.neolab.mvvm_architecture.databinding.ActivityHomeBinding

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 */
class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(HomeViewModel::class) {

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(inflater)
    }

    override fun initialize() {
    }

    override fun onSubscribeObserver() {
        super.onSubscribeObserver()
    }
}
