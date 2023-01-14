package com.android.randomjokes.data.source.remote

import retrofit2.Call
import retrofit2.http.GET

interface JokeApiService {

    @GET("api")
    fun getJoke(): Call<String>
}