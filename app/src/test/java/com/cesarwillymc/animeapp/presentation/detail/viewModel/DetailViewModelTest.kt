package com.cesarwillymc.animeapp.presentation.detail.viewModel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.cesarwillymc.animeapp.domain.usecase.DeleteCharacterUseCase
import com.cesarwillymc.animeapp.domain.usecase.GetCharacterDetailUseCase
import com.cesarwillymc.animeapp.domain.usecase.SaveCharacterUseCase
import com.cesarwillymc.animeapp.ui.navigation.route.HomeRoute
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.utils.BaseViewModelTest
import com.cesarwillymc.animeapp.utils.data.CharacterGeneratorTest.characterID
import com.cesarwillymc.animeapp.utils.data.UseCaseCharacterGeneratorTest.characterDetail
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest : BaseViewModelTest() {
    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @RelaxedMockK
    private lateinit var saveCharacterUseCase: SaveCharacterUseCase

    @RelaxedMockK
    private lateinit var deleteCharacterUseCase: DeleteCharacterUseCase

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        every { savedStateHandle.get<String>(HomeRoute.DETAIL_ID) } returns characterID
        coEvery { getCharacterDetailUseCase(characterID) } returns Result.Success(characterDetail)
        viewModel = DetailViewModel(
            savedStateHandle,
            getCharacterDetailUseCase,
            saveCharacterUseCase,
            deleteCharacterUseCase
        )
    }

    @Test
    fun getOpenBottomSheet() = runTest {
        viewModel.openBottomSheet.test {
            assertFalse(awaitItem())
            viewModel.onOpenBottomSheet()
            assertTrue(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getDetailUiState() = runTest {
        viewModel.detailUiState.test {
            val character = awaitItem()
            assertTrue(character.isSuccess)
            assertEquals(characterDetail.id, character.detail?.id)
            assertEquals(characterDetail.name, character.detail?.name)
            assertEquals(characterDetail.species, character.detail?.species)
            assertEquals(characterDetail.status, character.detail?.status)
        }
    }

    @Test
    fun onLoadArgument() {
        assertEquals(viewModel.characterId.value, characterID)
    }

    @Test
    fun onLoadDetailPresentationError() = runTest {
        coEvery { getCharacterDetailUseCase(characterID) } returns Result.Error(Exception())

        viewModel.onLoadDetailPresentation()
        viewModel.detailUiState.test {
            val character = awaitItem()
            assertTrue(character.isError)
        }
    }

    @Test
    fun removeFavoriteCharacter() = runTest {
        coEvery { deleteCharacterUseCase(characterID) } returns Result.Success(Unit)
        viewModel.removeFavoriteCharacter(characterID)
        viewModel.detailUiState.test {
            val character = awaitItem()
            assertFalse(character.detail?.isWishlist ?: true)
        }
    }

    @Test
    fun removeFavoriteCharacterError() = runTest {
        coEvery { deleteCharacterUseCase(characterID) } returns Result.Error(Exception())
        viewModel.removeFavoriteCharacter(characterID)
        viewModel.detailUiState.test {
            val character = awaitItem()
            assertEquals(characterDetail.isWishlist, character.detail?.isWishlist)
        }
    }

    @Test
    fun addFavoriteCharacter() = runTest {
        coEvery { saveCharacterUseCase(characterDetail) } returns Result.Success(Unit)
        viewModel.addFavoriteCharacter(characterDetail)
        viewModel.detailUiState.test {
            val character = awaitItem()
            assertTrue(character.detail?.isWishlist ?: false)
        }
    }

    @Test
    fun addFavoriteCharacterError() = runTest {
        coEvery { saveCharacterUseCase(characterDetail) } returns Result.Error(Exception())
        viewModel.addFavoriteCharacter(characterDetail)
        viewModel.detailUiState.test {
            val character = awaitItem()
            assertFalse(character.detail?.isWishlist ?: true)
        }
    }

    @Test
    fun onCloseBottomSheet() = runTest {
        viewModel.onOpenBottomSheet()
        viewModel.openBottomSheet.test {
            assertTrue(awaitItem())
            viewModel.onCloseBottomSheet()
            assertFalse(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
