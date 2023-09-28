package com.cesarwillymc.animeapp.presentation.detail.events

import com.cesarwillymc.animeapp.presentation.detail.entity.CharacterDetailPresentation

data class DetailUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val detail: CharacterDetailPresentation? = null,
    val isError: Boolean = false,
)
