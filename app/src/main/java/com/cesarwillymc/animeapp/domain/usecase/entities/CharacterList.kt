package com.cesarwillymc.animeapp.domain.usecase.entities

data class CharacterList(
    val next: Int?,
    val items: List<CharacterItem>
)
