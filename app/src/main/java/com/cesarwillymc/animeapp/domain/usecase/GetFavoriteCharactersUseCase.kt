package com.cesarwillymc.animeapp.domain.usecase

import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.di.IoDispatcher
import com.cesarwillymc.animeapp.domain.base.FlowUseCase
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteCharactersUseCase @Inject constructor(
    private val repository: CharacterDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<CharacterItem>>(
    dispatcher
) {
    override fun execute(parameters: Unit): Flow<List<CharacterItem>> {
        return repository.getCharactersFromDB()
    }
}
