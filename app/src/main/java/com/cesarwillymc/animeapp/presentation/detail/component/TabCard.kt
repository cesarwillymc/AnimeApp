package com.cesarwillymc.animeapp.presentation.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.ui.components.CustomExtendedSheetContent
import com.cesarwillymc.animeapp.ui.theme.AnimeAppTheme
import com.cesarwillymc.animeapp.util.constants.EMPTY_STRING

@Composable
fun TabCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = EMPTY_STRING,
    drawable: Painter,
    enabledSubContent: Boolean = true,
    subContent: @Composable () -> Unit = {}
) {
    val context = LocalContext.current
    val dimen = dimensionResource(id = R.dimen.Small120)
    val dimenNormal125 = dimensionResource(id = R.dimen.Normal125)
    var showSubContent by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = modifier
            .clickable(enabledSubContent) {
                showSubContent = !showSubContent
            }
            .fillMaxWidth()
            .semantics {
                contentDescription = context.getString(R.string.desc_onclick, title)
            }
    ) {
        val (card, text, icon) = createRefs()
        Card(
            modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(text.top)
                    bottom.linkTo(text.bottom)
                    start.linkTo(parent.start, margin = dimen)
                }
                .size(dimensionResource(id = R.dimen.ImageSuperMini))
        ) {
            Image(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.Small120)),
                painter = drawable,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, margin = dimenNormal125)
                bottom.linkTo(parent.bottom, margin = dimenNormal125)
                start.linkTo(card.end, margin = dimen)
                end.linkTo(icon.start, margin = dimen)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start
            )

            if (showSubContent) {
                subContent()
            }

        }
        Icon(
            if (showSubContent) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = stringResource(R.string.desc_arrow_select),
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(text.top)
                    bottom.linkTo(text.bottom)
                    end.linkTo(parent.end, margin = dimen)
                }
                .size(dimen)
        )
    }
}

@Composable
@Preview(name = "Dark Theme", showBackground = true)
fun TabCardDarkPreview() {
    AnimeAppTheme(darkTheme = true) { // Use your custom theme with darkTheme set to false
        TabCard(
            title = "Earth (Replacement Dimension)",
            drawable = painterResource(R.drawable.ic_galaxy),
            subtitle = stringResource(R.string.til_origin),
            enabledSubContent = true,
            subContent = {
                Text(
                    text = "Replacement Dimension",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = "Planet",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start
                )

            }
        )
    }
}