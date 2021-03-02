package com.neolab.mvvm_architecture.di

import com.neolab.mvvm_architecture.ui.home.HomeViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 */

val viewModelModule = module {
    viewModel<HomeViewModel>()
}
