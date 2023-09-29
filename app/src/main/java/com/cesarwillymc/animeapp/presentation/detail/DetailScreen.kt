package com.cesarwillymc.animeapp.presentation.detail

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.presentation.detail.component.DetailContent
import com.cesarwillymc.animeapp.presentation.detail.viewModel.DetailViewModel
import com.cesarwillymc.animeapp.ui.components.CustomExtendedSheetContent
import com.cesarwillymc.animeapp.ui.components.CustomFullScreenLoading

@Composable
@ExperimentalMaterial3Api
fun DetailScreen(
    detailViewModel: DetailViewModel,
    navigateUp: () -> Unit
) {

    val detailUiState by detailViewModel.detailUiState.collectAsState()
    val isOpenModal by detailViewModel.openBottomSheet.collectAsState()

    LaunchedEffect(Unit) {
//        detailViewModel.on()
    }

    DetailContent(
        detailUiState = detailUiState,
        onClickFavorite = detailViewModel::onClickFavorite,
        onNavigateUp = navigateUp,
        onClickRetry = detailViewModel::onLoadDetailPresentation
    )
    CustomFullScreenLoading(
        detailUiState.isLoading
    )
    if (isOpenModal)
        ModalBottomSheet(
            shape = RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.Normal100),
                topEnd = dimensionResource(id = R.dimen.Normal100)
            ),
            content = {
                CustomExtendedSheetContent(
                    icon = R.drawable.ic_wishlist_red,
                    title = stringResource(R.string.til_added_in_favorite),
                    subtitle = stringResource(R.string.lbl_added_in_favorite),
                    forceBigSize = true
                )
            },
            onDismissRequest = detailViewModel::onCloseBottomSheet,
        )
}