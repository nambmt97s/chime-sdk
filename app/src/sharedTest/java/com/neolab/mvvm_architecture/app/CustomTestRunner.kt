package com.neolab.mvvm_architecture.app

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 * Set to app gradle:
 * testInstrumentationRunner = "com.neolab.mvvm_architecture.app.CustomTestRunner"
 */
class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(classLoader, TestApp::class.java.name, context)
    }
}
