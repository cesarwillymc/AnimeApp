package com.cesarwillymc.animeapp.data.sources.character.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.cesarwillymc.CharacterQuery
import com.cesarwillymc.CharactersQuery
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterDetailResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterListResponse
import com.cesarwillymc.animeapp.data.sources.character.mapper.toDetail
import com.cesarwillymc.animeapp.data.sources.character.mapper.toList
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.getResult

class ApolloCharacterClient(
    private val apollo: ApolloClient
) : CharacterClient {
    override suspend fun getCharacters(page: Int): Result<CharacterListResponse> {
        return getResult {
            apollo.query(CharactersQuery(Optional.present(page)))
                .execute()
                .data
                ?.characters
                ?.toList()
                ?: CharacterListResponse(
                    next = null,
                    items = listOf()
                )
        }
    }
    override suspend fun getDetail(characterId: String): Result<CharacterDetailResponse?> {
        return getResult {
            apollo.query(CharacterQuery(characterId))
                .execute()
                .data
                ?.character
                ?.toDetail()
        }
    }
}