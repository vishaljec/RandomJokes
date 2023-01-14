package com.android.randomjokes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.randomjokes.domain.model.ApiError
import com.android.randomjokes.domain.usecase.GetJokeUseCase
import com.android.randomjokes.domain.usecase.base.UseCaseResponse
import kotlinx.coroutines.cancel

private const val MAX_JOKES = 10

class JokeViewModel constructor(private val jokeUseCase: GetJokeUseCase) : ViewModel() {

    val jokesData = MutableLiveData<List<String>>()
    private val jokes = ArrayList<String>()
    val messageData = MutableLiveData<String>()
    fun getJoke() {
        jokeUseCase.invoke(
            viewModelScope, null,
            object : UseCaseResponse<String> {
                override fun onSuccess(call: String) {
                    checkForMaxJoke()
                    jokes.add(call)
                    jokesData.value = jokes
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                }
            }
        )
    }

    private fun checkForMaxJoke() {
        if (jokes.size >= MAX_JOKES) {
            jokes.removeAt(0)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}