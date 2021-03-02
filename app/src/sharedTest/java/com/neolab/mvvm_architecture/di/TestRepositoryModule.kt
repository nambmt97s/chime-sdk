package com.neolab.mvvm_architecture.di

import com.neolab.mvvm_architecture.repository.task.DefaultTaskRepository
import com.neolab.mvvm_architecture.repository.task.TaskRepository
import org.koin.dsl.module

val testRepositoryModule = module {
    single { providerFakeTaskRepository() }
}

fun providerFakeTaskRepository(): TaskRepository {
    return DefaultTaskRepository()
}
