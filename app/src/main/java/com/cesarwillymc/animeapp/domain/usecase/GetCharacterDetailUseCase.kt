package com.cesarwillymc.animeapp.domain.usecase

import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.di.IoDispatcher
import com.cesarwillymc.animeapp.domain.base.SuspendUseCase
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: CharacterDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : SuspendUseCase<String, CharacterDetail?>(
    dispatcher
) {
    override suspend fun execute(parameters: String) = repository.getDetail(parameters)
}
