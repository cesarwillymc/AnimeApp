package com.cesarwillymc.animeapp.presentation.wishlist.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterItem
import com.cesarwillymc.animeapp.presentation.home.component.CharacterCard
import com.cesarwillymc.animeapp.ui.components.CustomLottieMessage
import com.cesarwillymc.animeapp.ui.components.CustomScaffold

@Composable
fun FavoriteContent(
    navigateToDetail: (String) -> Unit,
    favoritesFlow: List<CharacterItem>
) {

    CustomScaffold(
        toolbarTitle = stringResource(id = R.string.til_favorite)
    ) { paddingValues ->
        if (favoritesFlow.isEmpty()) {
            CustomLottieMessage(
                lottie = R.raw.animation_empty,
                title = stringResource(R.string.til_no_favorites),
                message = stringResource(R.string.desc_no_favorites)
            )
        } else
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(dimensionResource(id = R.dimen.Normal100))
            ) {
                items(favoritesFlow) { items ->
                    CharacterCard(
                        character = items,
                        onclickCharacter = navigateToDetail
                    )
                }
            }
    }

}
