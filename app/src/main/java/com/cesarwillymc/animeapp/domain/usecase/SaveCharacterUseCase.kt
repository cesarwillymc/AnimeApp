package com.cesarwillymc.animeapp.domain.usecase

import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.di.IoDispatcher
import com.cesarwillymc.animeapp.domain.base.SuspendUseCase
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterList
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveCharacterUseCase @Inject constructor(
    private val repository: CharacterDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : SuspendUseCase<CharacterDetail, Unit>(
    dispatcher
) {
    override suspend fun execute(parameters: CharacterDetail) = repository.addCharacterDB(parameters)
}
