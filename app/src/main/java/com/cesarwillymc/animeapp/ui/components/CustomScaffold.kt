package com.cesarwillymc.animeapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.util.constants.EMPTY_STRING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    modifier: Modifier = Modifier,
    toolbarTitle: String = EMPTY_STRING,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier, topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = toolbarTitle,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                modifier = modifier
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        content = content
    )
}
