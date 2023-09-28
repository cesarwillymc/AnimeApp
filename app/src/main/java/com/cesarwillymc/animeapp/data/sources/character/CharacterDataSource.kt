package com.cesarwillymc.animeapp.data.sources.character

import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterList
import com.cesarwillymc.animeapp.util.state.Result

interface CharacterDataSource {
    suspend fun getCharacters(page: Int): Result<CharacterList>
    suspend fun getDetail(id: String): Result<CharacterDetail?>
}