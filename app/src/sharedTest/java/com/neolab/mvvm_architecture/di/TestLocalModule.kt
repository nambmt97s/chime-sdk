package com.neolab.mvvm_architecture.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.neolab.mvvm_architecture.data.local.sharedpfers.SharedPrefsWrapper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val TEST_SHARED_PREFERENCE_FILE_NAME = "TestSharedPreference"

val testLocalModule = module {
    single { providerTestSharedPrefs(androidApplication()) }
    single { providerTestSharedPrefsWrapper(get(), get()) }
}

fun providerTestSharedPrefs(app: Application): SharedPreferences {
    // Make new test scope preference file to avoid change in original preference file.
    return app.getSharedPreferences(
        TEST_SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE
    )
}

fun providerTestSharedPrefsWrapper(
    sharedPreferences: SharedPreferences,
    gson: Gson
): SharedPrefsWrapper {
    return SharedPrefsWrapper(sharedPreferences, gson)
}
