package com.cesarwillymc.animeapp.data.sources.character.entities

data class CharacterListResponse(
    val next: Int?,
    val items: List<CharacterItemResponse>
)
