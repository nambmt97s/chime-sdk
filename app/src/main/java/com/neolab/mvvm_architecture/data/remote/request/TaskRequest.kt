package com.neolab.mvvm_architecture.data.remote.request

import com.google.gson.annotations.SerializedName

class TaskRequest(
    @SerializedName("task_name")
    val taskName: Any
)
