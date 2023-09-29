package com.cesarwillymc.animeapp.presentation.detail.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarwillymc.animeapp.domain.usecase.DeleteCharacterUseCase
import com.cesarwillymc.animeapp.domain.usecase.GetCharacterDetailUseCase
import com.cesarwillymc.animeapp.domain.usecase.SaveCharacterUseCase
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.presentation.detail.events.DetailUiState
import com.cesarwillymc.animeapp.ui.navigation.route.HomeRoute
import com.cesarwillymc.animeapp.ui.splitIo.SplitConfig
import com.cesarwillymc.animeapp.util.constants.EMPTY_STRING
import com.cesarwillymc.animeapp.util.constants.EVENT_FAVORITE
import com.cesarwillymc.animeapp.util.constants.EVENT_REM_FAVORITE
import com.cesarwillymc.animeapp.util.constants.TYPE_ACCOUNT
import com.cesarwillymc.animeapp.util.constants.TYPE_IDENTIFIER
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.getData
import com.cesarwillymc.animeapp.util.state.isSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val saveCharacterUseCase: SaveCharacterUseCase,
    private val deleteCharacterUseCase: DeleteCharacterUseCase
) : ViewModel() {
    private val _detailUiState = MutableStateFlow(DetailUiState())
    private val _openBottomSheet = MutableStateFlow(false)

    val openBottomSheet = _openBottomSheet.asStateFlow()
    val detailUiState = _detailUiState.asStateFlow()
    private var characterId = MutableStateFlow(EMPTY_STRING)

    init {
        onLoadArgument()
    }

    fun onLoadArgument() {
        savedStateHandle.get<String>(HomeRoute.DETAIL_ID)?.let { id ->
            characterId.update { id }
            onLoadDetailPresentation()
        }
    }

    fun onLoadDetailPresentation() {
        viewModelScope.launch {
            _detailUiState.update { it.copy(isLoading = true) }
            getCharacterDetailUseCase(characterId.value).let { result ->
                when (result) {
                    is Result.Error -> {
                        _detailUiState.update { DetailUiState(isError = true) }
                    }

                    is Result.Success -> {
                        _detailUiState.update {
                            it.copy(
                                detail = result.getData(),
                                isLoading = false,
                                isSuccess = true
                            )
                        }
                    }
                }
            }
        }
    }

    fun onClickFavorite() {
        detailUiState.value.detail?.let {
            if (it.isWishlist) {
                removeFavoriteCharacter(it.id)
            } else {
                addFavoriteCharacter(it)
            }
        }

    }

    fun removeFavoriteCharacter(id: String) {
        viewModelScope.launch {
            deleteCharacterUseCase(id).let {
                if (it.isSuccess) {
                    _detailUiState.update { update ->
                        DetailUiState(
                            detail = update.detail?.copy(
                                isWishlist = false
                            )
                        )
                    }
                    SplitConfig.splitClient?.track(
                        TYPE_ACCOUNT,
                        EVENT_REM_FAVORITE,
                        mapOf(
                            TYPE_IDENTIFIER to id
                        )
                    )

                }
            }
        }
    }

    fun addFavoriteCharacter(detail: CharacterDetail) {
        viewModelScope.launch {
            saveCharacterUseCase(detail).let {
                if (it.isSuccess) {
                    _detailUiState.update { update ->
                        DetailUiState(
                            detail = update.detail?.copy(
                                isWishlist = true
                            )
                        )
                    }
                    SplitConfig.splitClient?.track(
                        TYPE_ACCOUNT,
                        EVENT_FAVORITE,
                        mapOf(
                            TYPE_IDENTIFIER to detail.id
                        )
                    )
                    onOpenBottomSheet()
                }
            }

        }
    }

    fun onCloseBottomSheet() {
        _openBottomSheet.update { false }
    }

    fun onOpenBottomSheet() {
        _openBottomSheet.update { true }
    }
}