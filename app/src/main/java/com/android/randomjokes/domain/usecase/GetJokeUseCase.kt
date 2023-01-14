package com.android.randomjokes.domain.usecase

import com.android.randomjokes.domain.repository.JokeRepository
import com.android.randomjokes.domain.usecase.base.UseCase

class GetJokeUseCase constructor(
    private val jokeRepository: JokeRepository
) : UseCase<String, Any?>() {

    override suspend fun run(params: Any?): String {
        return jokeRepository.getJoke()
    }
}