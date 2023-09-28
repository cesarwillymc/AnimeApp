package com.cesarwillymc.animeapp.data.sources.character

import com.cesarwillymc.animeapp.data.sources.character.local.CharacterDao
import com.cesarwillymc.animeapp.data.sources.character.mapper.toCharacterDetailDomain
import com.cesarwillymc.animeapp.data.sources.character.mapper.toDatabase
import com.cesarwillymc.animeapp.data.sources.character.mapper.toListCharacterDomain
import com.cesarwillymc.animeapp.data.sources.character.remote.CharacterClient
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterItem
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterList
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.getResult
import com.cesarwillymc.animeapp.util.state.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepository(
    private val client: CharacterClient,
    private val characterDao: CharacterDao
) : CharacterDataSource {
    override suspend fun getCharacters(page: Int): Result<CharacterList> =
        client.getCharacters(page).map {
            toListCharacterDomain()
        }

    override suspend fun getDetail(id: String): Result<CharacterDetail?> =
        client.getDetail(id).map {
            toCharacterDetailDomain()
        }

    override suspend fun getCharactersFromDB(): Flow<List<CharacterItem>> {
        return characterDao.getAllCharacters().map { items ->
            items.toListCharacterDomain()
        }
    }
    override suspend fun getDetailFromDB(id: String): Result<CharacterDetail?> =
        getResult { characterDao.getCharacterById(id).toListCharacterDomain() }

    override suspend fun addCharacterDB(character: CharacterDetail): Result<Unit> =
        getResult { characterDao.insert(character = character.toDatabase()) }

    override suspend fun deleteCharacterDB(characterId: String): Result<Unit> =
        getResult { characterDao.delete(characterId) }

}