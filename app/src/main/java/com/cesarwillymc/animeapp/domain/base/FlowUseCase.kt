package com.cesarwillymc.animeapp.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, out Results>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    operator fun invoke(parameters: Params): Flow<Results> {
        return execute(parameters)
            .flowOn(coroutineDispatcher)
    }

    abstract fun execute(parameters: Params): Flow<Results>
}
