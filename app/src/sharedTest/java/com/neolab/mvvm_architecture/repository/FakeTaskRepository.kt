package com.neolab.mvvm_architecture.repository

import com.neolab.mvvm_architecture.data.remote.response.TaskResponse
import com.neolab.mvvm_architecture.repository.task.TaskRepository

class FakeTaskRepository(var shouldReturnError: Boolean = false) : TaskRepository {

    private val photosServiceData: LinkedHashMap<String, TaskResponse> = LinkedHashMap()
}
