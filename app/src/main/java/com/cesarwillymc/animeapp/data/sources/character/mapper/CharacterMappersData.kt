package com.cesarwillymc.animeapp.data.sources.character.mapper

import com.cesarwillymc.CharacterByIdQuery
import com.cesarwillymc.CharactersQuery
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterDetailResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterItemResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterListResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.EpisodeResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.LocationResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.OriginResponse
import com.cesarwillymc.animeapp.data.sources.character.local.entities.CharacterDetailEntity
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.domain.usecase.entities.Episode


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

fun CharacterDetail.toDatabase(): CharacterDetailEntity {
    return CharacterDetailEntity(
            created = created,
            gender = gender,
            id = id,
            image = image,
            name = name,
            species = species,
            status = status,
            type = type,
            location = location?.let {
                LocationResponse(
                    it.dimension,
                    it.name,
                    it.type
                )
            },
            episode = episode?.toListEpisodeResponse(),
            origin = origin?.let {
                OriginResponse(
                    it.dimension,
                    it.name,
                    it.type
                )
            }
        )

}

fun List<Episode?>.toListEpisodeResponse(): List<EpisodeResponse> {
    return mapNotNull { item ->
        item?.let {
            EpisodeResponse(
                airDate = it.airDate,
                created = it.created,
                episode = it.episode,
                name = it.name
            )
        }
    }
}