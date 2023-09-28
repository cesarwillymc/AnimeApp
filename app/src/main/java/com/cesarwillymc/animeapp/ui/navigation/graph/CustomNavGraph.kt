package com.cesarwillymc.animeapp.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cesarwillymc.animeapp.presentation.detail.DetailScreen
import com.cesarwillymc.animeapp.presentation.gift.GiftScreen
import com.cesarwillymc.animeapp.presentation.home.HomeScreen
import com.cesarwillymc.animeapp.presentation.wishlist.WishlistScreen
import com.cesarwillymc.animeapp.ui.navigation.action.HomeAction
import com.cesarwillymc.animeapp.ui.navigation.deeplink.generateDeepLinks
import com.cesarwillymc.animeapp.ui.navigation.route.BottomAppBarRoute
import com.cesarwillymc.animeapp.ui.navigation.route.HomeRoute


@Composable
fun CustomNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    val homeActions = remember(navController) { HomeAction(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = BottomAppBarRoute.Main.path,
            deepLinks = BottomAppBarRoute.Main.path.generateDeepLinks()
        ) {
            HomeScreen(
                navigateToDetail = homeActions.navigateToDetail,
                homeViewModel = hiltViewModel()
            )
        }

        composable(
            route = BottomAppBarRoute.Wishlist.path,
            deepLinks = BottomAppBarRoute.Wishlist.path.generateDeepLinks()
        ) {
            WishlistScreen(
                wishlistViewModel = hiltViewModel()
            )
        }

        composable(
            route = BottomAppBarRoute.Gift.path,
            deepLinks = BottomAppBarRoute.Gift.path.generateDeepLinks()
        ) {
            GiftScreen(
                viewModel = hiltViewModel()
            )
        }

        composable(
            route = HomeRoute.Detail.path,
            deepLinks = HomeRoute.Detail.path.generateDeepLinks()
        ) {
            DetailScreen(
                detailViewModel = hiltViewModel(),
                navigateUp = navController::navigateUp
            )
        }

    }
}
