package com.cesarwillymc.animeapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.ui.navigation.action.BottomAppBarAction
import com.cesarwillymc.animeapp.ui.navigation.route.BottomAppBarRoute
import com.cesarwillymc.animeapp.ui.theme.AnimeAppTheme
import com.cesarwillymc.animeapp.util.constants.EMPTY_STRING

@Composable
fun CustomExtendedSheetContent(
    @DrawableRes icon: Int,
    title: String,
    subtitle: String,
    onClick: () -> Unit = {},
    titleButton: String = EMPTY_STRING,
    forceBigSize: Boolean = false,
    onSecondButtonClick: () -> Unit = {},
    titleSecondButton: String = EMPTY_STRING
) {
    Box(contentAlignment = Alignment.Center) {
        GreenDialogSheetContentBody(
            title = title,
            subtitle = subtitle,
            icon = icon,
            forceBigSize = forceBigSize,
            onClick = onClick,
            titleButton = titleButton,
            onSecondButtonClick = onSecondButtonClick,
            titleSecondButton = titleSecondButton
        )
    }
}

@Composable
fun GreenDialogSheetContentBody(
    subtitle: String,
    title: String,
    @DrawableRes icon: Int,
    forceBigSize: Boolean,
    onClick: () -> Unit = {},
    titleButton: String = EMPTY_STRING,
    onSecondButtonClick: () -> Unit = {},
    titleSecondButton: String = EMPTY_STRING
) {
    val modifierIcon = Modifier.padding(dimensionResource(id = R.dimen.Normal100))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            dimensionResource(id = R.dimen.Normal100)
        )
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = if (forceBigSize) modifierIcon.size(dimensionResource(id = R.dimen.ImageNormal)) else modifierIcon
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.Normal100))
                .padding(bottom = dimensionResource(id = R.dimen.Normal100)),
            textAlign = TextAlign.Center
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.Normal100))
                .padding(horizontal = dimensionResource(id = R.dimen.Normal100)),
            textAlign = TextAlign.Center
        )
        if (titleButton.isNotEmpty()) {
            HorizontalDivider(thickness = dimensionResource(id = R.dimen.OneDp))
            CustomPrimaryButton(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.Normal100)),
                title = titleButton,
                textColor = MaterialTheme.colorScheme.secondary,
                onClick = onClick
            )
        }
        if (titleSecondButton.isNotEmpty()) {
            GreenOutlineButton(
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.Normal100),
                    end = dimensionResource(id = R.dimen.Normal100),
                    start = dimensionResource(id = R.dimen.Normal100)
                ),
                title = titleSecondButton,
                onClick = onSecondButtonClick
            )
        }
    }
}

@Composable
@Preview(name = "Dark Theme", showBackground = true)
fun CustomSheetContentDarkPreview() {
    AnimeAppTheme(darkTheme = true) { // Use your custom theme with darkTheme set to false
        CustomExtendedSheetContent(
            icon = R.drawable.ic_wishlist_red,
            title = stringResource(R.string.til_added_in_favorite),
            subtitle = stringResource(R.string.lbl_added_in_favorite),
            forceBigSize = true
        )
    }
}
