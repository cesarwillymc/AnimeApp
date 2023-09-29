package com.cesarwillymc.animeapp.presentation.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.cesarwillymc.animeapp.presentation.favorite.components.FavoriteContent
import com.cesarwillymc.animeapp.presentation.favorite.viewModel.FavoritesViewModel

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoritesViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val favoritesFlow by favoriteViewModel.favorites.collectAsState(listOf())

    FavoriteContent(
        navigateToDetail = navigateToDetail,
        favoritesFlow = favoritesFlow
    )
}