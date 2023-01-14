package com.android.randomjokes.domain.usecase.base

import com.android.randomjokes.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}

