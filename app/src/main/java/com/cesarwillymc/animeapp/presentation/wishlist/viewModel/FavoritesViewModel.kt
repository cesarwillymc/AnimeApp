package com.cesarwillymc.animeapp.presentation.wishlist.viewModel

import androidx.lifecycle.ViewModel
import com.cesarwillymc.animeapp.domain.usecase.GetFavoriteCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase
) : ViewModel() {

    private val _favorites = getFavoriteCharactersUseCase(Unit)
    val favorites get() = _favorites
}