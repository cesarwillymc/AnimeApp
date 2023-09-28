package com.cesarwillymc.animeapp.presentation.detail.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarwillymc.animeapp.domain.usecase.GetCharacterDetailUseCase
import com.cesarwillymc.animeapp.ui.navigation.route.HomeRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    init {
        savedStateHandle.onLoadArgument()
    }
    private fun SavedStateHandle.onLoadArgument() {
        viewModelScope.launch {
            get<String>(HomeRoute.DETAIL_ID)?.let { id ->
                onLoadDetailPresentation(id= id)
            }
        }
    }

    fun onLoadDetailPresentation(id: String) {

    }
}