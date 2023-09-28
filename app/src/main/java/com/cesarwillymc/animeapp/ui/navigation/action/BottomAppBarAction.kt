package com.cesarwillymc.animeapp.ui.navigation.action

import androidx.navigation.NavHostController
import com.cesarwillymc.animeapp.ui.navigation.route.BottomAppBarRoute

class BottomAppBarAction(navController: NavHostController) {

    val navigateToMain: () -> Unit = {
        navController.navigate(BottomAppBarRoute.Main.path)
    }
    val navigateToGift: () -> Unit = {
        navController.navigate(BottomAppBarRoute.Gift.path)
    }
    val navigateToWishlist: () -> Unit = {
        navController.navigate(BottomAppBarRoute.Wishlist.path)
    }
}
