package com.neolab.mvvm_architecture.di

import android.app.Application
import android.content.res.Resources
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.neolab.mvvm_architecture.data.remote.api.middleware.BooleanAdapter
import com.neolab.mvvm_architecture.data.remote.api.middleware.DoubleAdapter
import com.neolab.mvvm_architecture.data.remote.api.middleware.IntegerAdapter
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 */

val appModule = module {
    single { provideResources(androidApplication()) }
    single { provideGson() }
}

fun provideResources(app: Application): Resources {
    return app.resources
}

fun provideGson(): Gson {
    val booleanAdapter = BooleanAdapter()
    val integerAdapter = IntegerAdapter()
    val doubleAdapter = DoubleAdapter()
    return GsonBuilder()
        .registerTypeAdapter(Boolean::class.java, booleanAdapter)
        .registerTypeAdapter(Int::class.java, integerAdapter)
        .registerTypeAdapter(Double::class.java, doubleAdapter)
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}
