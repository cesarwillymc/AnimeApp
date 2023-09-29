package com.cesarwillymc.animeapp.presentation.gift

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterItem
import com.cesarwillymc.animeapp.presentation.gift.components.GiftContent
import com.cesarwillymc.animeapp.presentation.home.component.CharacterCard
import com.cesarwillymc.animeapp.ui.theme.AnimeAppTheme

@Composable
fun GiftScreen() {
    GiftContent()
}
