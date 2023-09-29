package com.cesarwillymc.animeapp.ui.navigation.action

import androidx.navigation.NavHostController
import com.cesarwillymc.animeapp.ui.navigation.route.BottomAppBarRoute
import com.cesarwillymc.animeapp.util.constants.ZERO

class BottomAppBarAction(navController: NavHostController) {

    val navigateToMain: () -> Unit = {
        navController.navigate(BottomAppBarRoute.Main.path)
    }
    val navigateToGift: () -> Unit = {
        navController.navigate(BottomAppBarRoute.Gift.path)
    }
    val navigateToFavorite: () -> Unit = {
        navController.navigate(BottomAppBarRoute.Favorite.path)
    }
    val navigateToMaintenance: () -> Unit = {
        navController.navigate(BottomAppBarRoute.Maintenance.path) {
            popUpTo(ZERO)
        }
    }
}
