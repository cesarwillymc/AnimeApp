package com.cesarwillymc.animeapp.data.sources.character

import com.cesarwillymc.animeapp.data.sources.character.mapper.toDomain
import com.cesarwillymc.animeapp.data.sources.character.remote.CharacterClient
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterList
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.map

class CharacterRepository(
    private val client: CharacterClient
) : CharacterDataSource {
    override suspend fun getCharacters(page: Int): Result<CharacterList> =
        client.getCharacters(page).map {
            toDomain()
        }

    override suspend fun getDetail(id: String): Result<CharacterDetail?> =
        client.getDetail(id).map {
            toDomain()
        }

}