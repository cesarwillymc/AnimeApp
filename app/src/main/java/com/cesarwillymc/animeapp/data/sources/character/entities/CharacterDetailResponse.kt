package com.cesarwillymc.animeapp.data.sources.character.entities

data class CharacterDetailResponse(
    val created: String,
    val gender: String,
    val id: String,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val location: LocationResponse?,
    val episode: List<EpisodeResponse>?,
    val origin: OriginResponse?
)