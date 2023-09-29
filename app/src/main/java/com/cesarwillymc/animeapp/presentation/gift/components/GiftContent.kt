package com.cesarwillymc.animeapp.presentation.gift.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.ui.components.CustomLottieMessage
import com.cesarwillymc.animeapp.ui.components.CustomScaffold

@Composable
fun GiftContent() {
    CustomScaffold(
        toolbarTitle = stringResource(R.string.til_gift),
    ) {
        CustomLottieMessage(
            lottie = R.raw.animation_android,
            title = stringResource(R.string.til_thank_you),
            message = stringResource(R.string.desc_thank_you)
        )
    }
}
