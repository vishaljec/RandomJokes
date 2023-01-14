package com.android.randomjokes.di

import com.android.randomjokes.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { JokeViewModel(get()) }

    single { createGetJokeUseCase(get()) }

    single { createRetrofitManager() }

    single { createJokeRepository(get(), get()) }
}