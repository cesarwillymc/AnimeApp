package com.cesarwillymc.animeapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.util.constants.EMPTY_STRING

@Composable
fun CustomSimpleScaffold(
    modifier: Modifier = Modifier,
    toolbarTitle: String = EMPTY_STRING,
    @DrawableRes backIcon: Int = R.drawable.ic_back,
    navigateUp: () -> Unit,
    content: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Scaffold(
        modifier = modifier.fillMaxSize(), topBar = {
            CustomTopAppBar(
                title = {
                    Text(
                        text = toolbarTitle,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateUp,
                        modifier = modifier
                            .padding(start = dimensionResource(id = R.dimen.Small150))
                            .clip(CircleShape)
                            .size(dimensionResource(id = R.dimen.Large100))
                    ) {
                        Icon(
                            painter = painterResource(id = backIcon),
                            contentDescription = stringResource(R.string.desc_back)
                        )
                    }
                },
                actions = actions
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(paddingValues = padding)) {
                content()
            }
        }
    )
}
