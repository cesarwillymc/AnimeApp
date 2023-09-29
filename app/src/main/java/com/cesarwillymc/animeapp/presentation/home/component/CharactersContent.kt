package com.cesarwillymc.animeapp.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterItem
import com.cesarwillymc.animeapp.ui.components.CustomScaffold

@Composable
fun CharactersContent(
    navigateToDetail: (String) -> Unit,
    charactersPagingList: LazyPagingItems<CharacterItem>
) {
    CustomScaffold(
        toolbarTitle = stringResource(id = R.string.til_home)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.Normal100))
        ) {
            items(charactersPagingList.itemCount) { index ->
                CharacterCard(
                    character = charactersPagingList[index],
                    onclickCharacter = navigateToDetail
                )
            }
            charactersPagingList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillMaxWidth()) }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item {
                            ErrorCard(
                                error = stringResource(id = R.string.til_error_load_items),
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { retry() }
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            ErrorCard(
                                error = stringResource(id = R.string.til_error_load_items),
                                onClickRetry = { retry() },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }

}
