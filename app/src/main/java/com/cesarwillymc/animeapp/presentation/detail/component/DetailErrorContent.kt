package com.cesarwillymc.animeapp.presentation.detail.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
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