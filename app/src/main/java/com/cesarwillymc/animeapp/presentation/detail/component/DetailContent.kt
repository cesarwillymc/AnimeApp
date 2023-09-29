package com.cesarwillymc.animeapp.presentation.detail.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.presentation.detail.events.DetailUiState
import com.cesarwillymc.animeapp.ui.components.CustomSimpleScaffold
import com.cesarwillymc.animeapp.util.extension.orEmpty

@Composable
fun DetailContent(
    detailUiState: DetailUiState,
    onNavigateUp: () -> Unit,
    onClickFavorite: () -> Unit,
    onClickRetry: () -> Unit
) {
    CustomSimpleScaffold(
        navigateUp = onNavigateUp,
        toolbarTitle = detailUiState.detail?.name.orEmpty(),
        actions = {
            IconButton(
                onClick = onClickFavorite
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_favorite
                    ),
                    contentDescription = stringResource(R.string.desc_btn_favorite),
                    tint = if (detailUiState.detail?.isWishlist.orEmpty()) Color.Red else Color.Gray
                )
            }
        },
        content = {
            if (detailUiState.detail != null && !detailUiState.isLoading) {
                DetailViewContent(
                    character = detailUiState.detail
                )
            } else if (!detailUiState.isLoading) {
                DetailErrorContent(onClickRetry)
            }
        }
    )
}
