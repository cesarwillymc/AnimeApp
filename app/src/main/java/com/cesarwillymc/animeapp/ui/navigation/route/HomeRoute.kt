package com.cesarwillymc.animeapp.ui.navigation.route

sealed class HomeRoute(path: String) : Route(path) {
    object Detail : HomeRoute(DETAIL_PATH)
    companion object {
        const val DETAIL_ID = "id"
        const val DETAIL_PATH = "detail/{$DETAIL_ID}"
    }
}
