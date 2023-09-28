package com.cesarwillymc.animeapp.ui.navigation.action

import androidx.navigation.NavHostController
import com.cesarwillymc.animeapp.ui.navigation.route.HomeRoute

class HomeAction(navController: NavHostController) {

    val navigateToDetail: (String) -> Unit = { id ->
        val routeReplace = HomeRoute.Detail.path.replace(
            "{${HomeRoute.DETAIL_ID}}",
            id
        )
        navController.navigate(routeReplace)
    }
}
