package com.cesarwillymc.animeapp.data.sources.character.mapper

import com.cesarwillymc.CharacterByIdQuery
import com.cesarwillymc.CharactersQuery
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterDetailResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterItemResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterListResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.EpisodeResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.LocationResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.OriginResponse


fun CharactersQuery.Characters.toList(): CharacterListResponse {
    return CharacterListResponse(
        next = info?.next,
        items = results.orEmpty().mapNotNull { item ->
            item?.let {
                CharacterItemResponse(
                    it.image.orEmpty(),
                    it.name.orEmpty(),
                    it.id.orEmpty(),
                    it.species.orEmpty(),
                    it.gender.orEmpty()
                )
            }
        }
    )
}

fun List<CharacterByIdQuery.Episode?>.toList(): List<EpisodeResponse> {
    return mapNotNull { item ->
        item?.let {
            EpisodeResponse(
                airDate = it.air_date.orEmpty(),
                created = it.created.orEmpty(),
                episode = it.episode.orEmpty(),
                name = it.name.orEmpty()
            )
        }
    }
}

fun CharacterByIdQuery.Character.toDetail(): CharacterDetailResponse {
    return CharacterDetailResponse(
        created = created.orEmpty(),
        gender = gender.orEmpty(),
        id = id.orEmpty(),
        image = image.orEmpty(),
        name = name.orEmpty(),
        species = species.orEmpty(),
        status = status.orEmpty(),
        type = type.orEmpty(),
        location = location?.let {
            LocationResponse(
                dimension = it.dimension.orEmpty(),
                name = it.name.orEmpty(),
                type = it.type.orEmpty()
            )
        },
        episode = episode.toList(),
        origin = origin?.let {
            OriginResponse(
                dimension = it.dimension.orEmpty(),
                name = it.name.orEmpty(),
                type = it.type.orEmpty()
            )
        }

    )
}