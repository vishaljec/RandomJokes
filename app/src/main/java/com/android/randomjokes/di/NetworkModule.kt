package com.android.randomjokes.di

import com.android.randomjokes.data.repository.JokeRepositoryImp
import com.android.randomjokes.data.source.remote.JokeApiService
import com.android.randomjokes.domain.repository.JokeRepository
import com.android.randomjokes.domain.repository.RetrofitApiManager
import com.android.randomjokes.domain.usecase.GetJokeUseCase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

private const val BASE_URL = "https://geek-jokes.sameerkumar.website/"

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BASE_URL) }

    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder().baseUrl(url).client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): JokeApiService {
    return retrofit.create(JokeApiService::class.java)
}

fun createJokeRepository(apiService: JokeApiService, retrofitApiManager: RetrofitApiManager): JokeRepository {
    return JokeRepositoryImp(apiService, retrofitApiManager)
}

fun createRetrofitManager() = RetrofitApiManager()

fun createGetJokeUseCase(jokeRepository: JokeRepository): GetJokeUseCase {
    return GetJokeUseCase(jokeRepository)
}
