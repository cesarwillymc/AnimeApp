package com.cesarwillymc.animeapp.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.ui.navigation.action.BottomAppBarAction
import com.cesarwillymc.animeapp.ui.navigation.route.BottomAppBarRoute
import com.cesarwillymc.animeapp.ui.theme.AnimeAppTheme
import com.cesarwillymc.animeapp.util.constants.ONE_F

@Composable
fun CustomBottomAppBar(
    actions: BottomAppBarAction,
    currentRoute: String,
    showGiftBottomAppBar: Boolean
) {
    BoxWithConstraints {

        Surface(tonalElevation = dimensionResource(id = R.dimen.Small100)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.TwoDp)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.Small150))
            ) {
                BottomAppBarIcon(
                    modifier = Modifier.weight(ONE_F),
                    icon = R.drawable.ic_home,
                    label = R.string.home_bottom_bar_label,
                    isSelected = currentRoute == BottomAppBarRoute.Main.path,
                    onClick = actions.navigateToMain
                )
                BottomAppBarIcon(
                    modifier = Modifier.weight(ONE_F),
                    icon = R.drawable.ic_wishlist,
                    label = R.string.wishlist_bottom_bar_label,
                    isSelected = currentRoute == BottomAppBarRoute.Wishlist.path,
                    onClick = actions.navigateToWishlist
                )
                if (showGiftBottomAppBar)
                    BottomAppBarIcon(
                        modifier = Modifier.weight(ONE_F),
                        icon = R.drawable.ic_gift,
                        label = R.string.gift_bottom_bar_label,
                        isSelected = currentRoute == BottomAppBarRoute.Gift.path,
                        onClick = actions.navigateToGift
                    )
            }
        }
    }
}

@Composable
fun BottomAppBarIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes label: Int,
    isSelected: Boolean,
    textSize: Dp = dimensionResource(id = R.dimen.TextMini),
    cellHeight: Dp = dimensionResource(id = R.dimen.BottomBarCellHeight),
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .size(cellHeight)
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.Small100)))
            .clickable(onClick = { if (!isSelected) onClick() }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surface
        )
        Text(
            text = stringResource(id = label),
            style = MaterialTheme.typography.bodySmall,
            fontSize = textSize.value.sp
        )
    }
}

@Composable
@Preview(name = "Light Theme", showBackground = true)
fun CustomBottomAppBarLightPreview() {
    AnimeAppTheme(darkTheme = false) { // Use your custom theme with darkTheme set to false
        val navController = rememberNavController()
        CustomBottomAppBar(
            actions = BottomAppBarAction(navController),
            currentRoute = BottomAppBarRoute.Main.path,
            showGiftBottomAppBar = true
        )
    }
}

@Composable
@Preview(name = "Dark Theme", showBackground = true)
fun CustomBottomAppBarDarkPreview() {
    AnimeAppTheme(darkTheme = true) { // Use your custom theme with darkTheme set to false
        val navController = rememberNavController()
        CustomBottomAppBar(
            actions = BottomAppBarAction(navController),
            currentRoute = BottomAppBarRoute.Main.path,
            showGiftBottomAppBar = true
        )
    }
}
