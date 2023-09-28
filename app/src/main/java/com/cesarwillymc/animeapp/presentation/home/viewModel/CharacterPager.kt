package com.cesarwillymc.animeapp.presentation.home.viewModel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cesarwillymc.animeapp.domain.usecase.GetCharactersUseCase
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterItem
import com.cesarwillymc.animeapp.util.constants.ONE
import com.cesarwillymc.animeapp.util.state.getData
import com.cesarwillymc.animeapp.util.state.getError
import com.cesarwillymc.animeapp.util.state.isSuccess


class CharacterPager(private val useCase: GetCharactersUseCase) :
    PagingSource<Int, CharacterItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        return try {
            val nextPage = params.key ?: INITIAL_OFFSET
            val characterListResponse = useCase(nextPage)
            if (characterListResponse.isSuccess) {
                LoadResult.Page(
                    data = characterListResponse.getData().items,
                    prevKey = if (nextPage == ONE) null else nextPage - ONE,
                    nextKey = characterListResponse.getData().next
                )
            } else {
                LoadResult.Error(characterListResponse.getError())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(ONE) ?: anchorPage?.nextKey?.minus(ONE)
        }
    }


    companion object {
        const val LIMIT = 20
        const val INITIAL_OFFSET = 0
    }
}
