package com.cesarwillymc.animeapp.data.sources.character.remote

import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterDetailResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterListResponse
import com.cesarwillymc.animeapp.util.state.Result

interface CharacterClient {
    suspend fun getCharacters(page: Int): Result<CharacterListResponse>
    suspend fun getDetail(characterId: String): Result<CharacterDetailResponse?>
}
