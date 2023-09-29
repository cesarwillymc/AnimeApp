package com.cesarwillymc.animeapp.presentation.detail.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.ui.components.CustomLottieMessage

@Composable
fun DetailErrorContent(onClickRetry: () -> Unit) {
    CustomLottieMessage(
        lottie = R.raw.animation_error,
        title = stringResource(R.string.lbl_error),
        message = stringResource(R.string.desc_error_data),
        showRetryButton = true,
        onClickRetry = onClickRetry
    )
}

@Composable
@Preview(name = "Light Theme", showBackground = true)
fun DetailErrorContentPreview() {
    CustomLottieMessage(
        lottie = R.raw.animation_error,
        title = stringResource(R.string.lbl_error),
        message = stringResource(R.string.desc_error_data),
        showRetryButton = true,
        onClickRetry = {  }
    )
}
