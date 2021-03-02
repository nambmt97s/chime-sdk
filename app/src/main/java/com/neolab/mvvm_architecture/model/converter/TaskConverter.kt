package com.neolab.mvvm_architecture.model.converter

import com.neolab.mvvm_architecture.data.local.entity.TaskEntity
import com.neolab.mvvm_architecture.data.remote.request.TaskRequest
import com.neolab.mvvm_architecture.data.remote.response.TaskResponse
import com.neolab.mvvm_architecture.model.uimodel.Task

object TaskConverter {

    fun convertTaskToTaskRequest(task: Task) = TaskRequest(
        taskName = task.name
    )

    fun convertTaskResponseToTask(taskResponse: TaskResponse) = Task(
        name = taskResponse.name
    )

    fun convertTaskEntityToTask(taskEntity: TaskEntity): Task = taskEntity
}
