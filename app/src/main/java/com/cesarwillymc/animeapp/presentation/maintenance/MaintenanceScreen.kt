package com.cesarwillymc.animeapp.presentation.maintenance

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.ui.components.CustomLottieMessage

@Composable
fun MaintenanceScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        CustomLottieMessage(
            lottie = R.raw.animation_security,
            title = stringResource(R.string.til_alert),
            message = stringResource(R.string.desc_alert)
        )
    }
}
