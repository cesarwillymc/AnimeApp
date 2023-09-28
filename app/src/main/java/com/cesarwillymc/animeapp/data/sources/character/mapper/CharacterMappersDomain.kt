package com.cesarwillymc.animeapp.data.sources.character.mapper

import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterDetailResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterListResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.EpisodeResponse
import com.cesarwillymc.animeapp.data.sources.character.local.entities.CharacterDetailEntity
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterItem
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterList
import com.cesarwillymc.animeapp.domain.usecase.entities.Episode
import com.cesarwillymc.animeapp.domain.usecase.entities.Location
import com.cesarwillymc.animeapp.domain.usecase.entities.Origin

fun CharacterListResponse.toListCharacterDomain(): CharacterList {
    return CharacterList(
        next = next,
        items = items.map {
            CharacterItem(
                image = it.image,
                name = it.name,
                id = it.id,
                species = it.species,
                gender = it.gender
            )
        }
    )
}

fun List<EpisodeResponse>.toEpisodeDomain(): List<Episode> {
    return mapNotNull {
        Episode(
            airDate = it.airDate,
            created = it.created,
            episode = it.episode,
            name = it.name
        )
    }
}

fun CharacterDetailResponse?.toCharacterDetailDomain(): CharacterDetail? {
    return this?.run {
        CharacterDetail(
            created = created,
            gender = gender,
            id = id,
            image = image,
            name = name,
            species = species,
            status = status,
            type = type,
            location = location?.let {
                Location(
                    dimension = it.dimension,
                    name = it.name,
                    type = it.type
                )
            },
            episode = episode.orEmpty().toEpisodeDomain(),
            origin = origin?.let {
                Origin(
                    dimension = it.dimension,
                    name = it.name,
                    type = it.type
                )
            }
        )
    }
}
fun CharacterDetailEntity?.toListCharacterDomain(): CharacterDetail? {
    return this?.run {
        CharacterDetail(
            created = created,
            gender = gender,
            id = id,
            image = image,
            name = name,
            species = species,
            status = status,
            type = type,
            location = location?.let {
                Location(
                    dimension = it.dimension,
                    name = it.name,
                    type = it.type
                )
            },
            episode = episode.orEmpty().toEpisodeDomain(),
            origin = origin?.let {
                Origin(
                    dimension = it.dimension,
                    name = it.name,
                    type = it.type
                )
            },
            isWishlist = true
        )
    }
}

fun List<CharacterDetailEntity>.toListCharacterDomain(): List<CharacterItem> {
    return map {
        CharacterItem(
            image = it.image,
            name = it.name,
            id = it.id,
            species = it.species,
            gender = it.gender
        )
    }
}