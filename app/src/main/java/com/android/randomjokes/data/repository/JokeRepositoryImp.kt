package com.android.randomjokes.data.repository

import com.android.randomjokes.data.source.remote.JokeApiService
import com.android.randomjokes.domain.repository.JokeRepository
import com.android.randomjokes.domain.repository.RetrofitApiManager

class JokeRepositoryImp(
    private val apiService: JokeApiService, private val retrofitApiManager: RetrofitApiManager
) : JokeRepository {

    override suspend fun getJoke(): String {
        val request = apiService.getJoke()
        return retrofitApiManager.call(request) ?: ""
    }
}