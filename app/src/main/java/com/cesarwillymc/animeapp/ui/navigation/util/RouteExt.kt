package com.cesarwillymc.animeapp.ui.navigation.util

import com.cesarwillymc.animeapp.ui.navigation.route.BottomAppBarRoute

fun String.isBottomRoute() = this in listOf(
    BottomAppBarRoute.Favorite.path,
    BottomAppBarRoute.Gift.path,
    BottomAppBarRoute.Main.path
)
