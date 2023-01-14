package com.android.randomjokes.domain.repository

interface JokeRepository {
    suspend fun getJoke(): String
}