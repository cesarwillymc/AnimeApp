package com.cesarwillymc.animeapp.ui.navigation.graph

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cesarwillymc.animeapp.presentation.detail.DetailScreen
import com.cesarwillymc.animeapp.presentation.gift.GiftScreen
import com.cesarwillymc.animeapp.presentation.home.HomeScreen
import com.cesarwillymc.animeapp.presentation.favorite.FavoriteScreen
import com.cesarwillymc.animeapp.ui.navigation.action.HomeAction
import com.cesarwillymc.animeapp.ui.navigation.deeplink.generateDeepLinks
import com.cesarwillymc.animeapp.ui.navigation.route.BottomAppBarRoute
import com.cesarwillymc.animeapp.ui.navigation.route.HomeRoute


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavGraph(
    navController: NavHostController,
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
            )
        }

        composable(
            route = BottomAppBarRoute.Favorite.path,
            deepLinks = BottomAppBarRoute.Favorite.path.generateDeepLinks()
        ) {
            FavoriteScreen(
                navigateToDetail = homeActions.navigateToDetail
            )
        }

        composable(
            route = BottomAppBarRoute.Gift.path,
            deepLinks = BottomAppBarRoute.Gift.path.generateDeepLinks()
        ) {
            GiftScreen(
            )
        }

        composable(
            route = HomeRoute.Detail.path,
            deepLinks = HomeRoute.Detail.path.generateDeepLinks()
        ) {
            DetailScreen(
                detailViewModel = hiltViewModel(),
                navigateUp = homeActions.navigateUp
            )
        }

    }
}
