package com.cesarwillymc.animeapp.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.cesarwillymc.animeapp.domain.usecase.GetCharactersUseCase
import com.cesarwillymc.animeapp.presentation.home.viewModel.CharacterPager.Companion.LIMIT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {
    val charactersList = Pager(PagingConfig(pageSize = LIMIT)) {
        CharacterPager(getCharactersUseCase)
    }.flow
}