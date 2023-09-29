package com.cesarwillymc.animeapp.presentation.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.cesarwillymc.animeapp.presentation.home.component.CharactersContent
import com.cesarwillymc.animeapp.presentation.home.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    navigateToDetail: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val charactersPagingList = homeViewModel.charactersList.collectAsLazyPagingItems()

    CharactersContent(
        navigateToDetail = navigateToDetail,
        charactersPagingList = charactersPagingList
    )
}