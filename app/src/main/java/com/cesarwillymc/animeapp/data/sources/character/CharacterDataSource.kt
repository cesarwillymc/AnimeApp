package com.cesarwillymc.animeapp.data.sources.character

import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterItem
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterList
import com.cesarwillymc.animeapp.util.state.Result
import kotlinx.coroutines.flow.Flow

interface CharacterDataSource {
    suspend fun getCharacters(page: Int): Result<CharacterList>
    suspend fun getDetail(id: String): Result<CharacterDetail?>
    suspend fun getCharactersFromDB(): Flow<List<CharacterItem>>
    suspend fun getDetailFromDB(id: String): Result<CharacterDetail?>
    suspend fun addCharacterDB(character: CharacterDetail): Result<Unit>
    suspend fun deleteCharacterDB(characterId: String): Result<Unit>
}