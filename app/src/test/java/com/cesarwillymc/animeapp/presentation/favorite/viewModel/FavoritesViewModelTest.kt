package com.cesarwillymc.animeapp.presentation.favorite.viewModel

import app.cash.turbine.test
import com.cesarwillymc.animeapp.domain.usecase.GetFavoriteCharactersUseCase
import com.cesarwillymc.animeapp.utils.BaseViewModelTest
import com.cesarwillymc.animeapp.utils.data.UseCaseCharacterGeneratorTest
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesViewModelTest : BaseViewModelTest() {

    @RelaxedMockK
    private lateinit var getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase
    private lateinit var viewModel: FavoritesViewModel
    private val characterList = UseCaseCharacterGeneratorTest.characterList.items
    private val flowOfCharacterList = MutableStateFlow(characterList)

    @Before
    fun setUp() {
        coEvery { getFavoriteCharactersUseCase(Unit) } returns flowOfCharacterList
        viewModel = FavoritesViewModel(getFavoriteCharactersUseCase)
    }

    @Test
    fun getFavorites() = runTest {
        viewModel.favorites.test {
            assertEquals(characterList.size, awaitItem().size)
            val updatedCharacterList = characterList + UseCaseCharacterGeneratorTest.newItem
            flowOfCharacterList.emit(updatedCharacterList)
            assertEquals(updatedCharacterList.size, awaitItem().size)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
