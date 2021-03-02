package com.neolab.mvvm_architecture.di

import com.neolab.mvvm_architecture.repository.task.DefaultTaskRepository
import com.neolab.mvvm_architecture.repository.task.TaskRepository
import org.koin.dsl.module

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 */

val repositoryModule = module {
    single { providerTaskRepository() }
}

fun providerTaskRepository(): TaskRepository {
    return DefaultTaskRepository()
}
