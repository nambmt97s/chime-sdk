/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.neolab.mvvm_architecture.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.test.core.app.ActivityScenario
import com.neolab.mvvm_architecture.utils.liveData.SingleEvent as Event
import org.junit.Assert.assertEquals

fun assertLiveDataEventTriggered(
    liveData: LiveData<Event<String>>,
    taskId: String
) {
    val value = LiveDataTestUtil.getValue(liveData)
    assertEquals(value.getContentIfNotHandled(), taskId)
}

inline fun <reified T : Activity> launch(intent: Intent): ActivityScenario<T> =
    ActivityScenario.launch(intent)

inline fun <reified T : Activity> launchActivity(
    context: Context,
    savedState: Bundle? = null,
    moreSetup: Intent.() -> Unit = {}
): ActivityScenario<T> =
    ActivityScenario.launch(Intent(context, T::class.java).apply { moreSetup(this) }, savedState)
