package com.cesarwillymc.animeapp.presentation.detail.events

import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail

data class DetailUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val detail: CharacterDetail? = null,
    val isError: Boolean = false
)
