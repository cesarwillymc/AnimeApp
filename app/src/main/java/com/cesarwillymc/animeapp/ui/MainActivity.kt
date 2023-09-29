package com.cesarwillymc.animeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cesarwillymc.animeapp.ui.components.CustomBottomAppBar
import com.cesarwillymc.animeapp.ui.navigation.action.BottomAppBarAction
import com.cesarwillymc.animeapp.ui.navigation.graph.CustomNavGraph
import com.cesarwillymc.animeapp.ui.navigation.route.BottomAppBarRoute
import com.cesarwillymc.animeapp.ui.navigation.util.isBottomRoute
import com.cesarwillymc.animeapp.ui.splitIo.SplitConfig
import com.cesarwillymc.animeapp.ui.theme.AnimeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplitConfig(this)
        setContent {
            AnimeAppTheme {
                val navController = rememberNavController()
                val bottomActions =
                    remember(navController) { BottomAppBarAction(navController) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute =
                    navBackStackEntry?.destination?.route ?: BottomAppBarRoute.Main.path
                val flagGifActive by SplitConfig.flagGifActive.collectAsState()
                Scaffold(
                    bottomBar = {
                        if (currentRoute.isBottomRoute())
                            CustomBottomAppBar(
                                actions = bottomActions,
                                currentRoute = currentRoute,
                                showGiftBottomAppBar = flagGifActive
                            )
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        CustomNavGraph(
                            navController = navController,
                            startDestination = BottomAppBarRoute.Main.path
                        )
                    }
                }
            }
        }

    }
}
