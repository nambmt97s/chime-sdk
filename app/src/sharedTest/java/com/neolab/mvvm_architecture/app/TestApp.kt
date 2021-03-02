package com.neolab.mvvm_architecture.app

import android.app.Application
import com.neolab.mvvm_architecture.di.appModule
import com.neolab.mvvm_architecture.di.testLocalModule
import com.neolab.mvvm_architecture.di.testRemoteModule
import com.neolab.mvvm_architecture.di.testRepositoryModule
import com.neolab.mvvm_architecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApp)
            modules(
                listOf(
                    appModule,
                    testRemoteModule,
                    testLocalModule,
                    testRepositoryModule,
                    viewModelModule
                )
            )
        }
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}
