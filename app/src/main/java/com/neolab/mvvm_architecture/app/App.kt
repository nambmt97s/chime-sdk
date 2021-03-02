package com.neolab.mvvm_architecture.app

import android.app.Application
import com.neolab.mvvm_architecture.BuildConfig
import com.neolab.mvvm_architecture.di.appModule
import com.neolab.mvvm_architecture.di.localModule
import com.neolab.mvvm_architecture.di.remoteModule
import com.neolab.mvvm_architecture.di.repositoryModule
import com.neolab.mvvm_architecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configTimber()
        configKoin()
    }

    private fun configKoin() {
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, remoteModule, localModule, repositoryModule, viewModelModule))
        }
    }

    private fun configTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
