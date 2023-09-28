package com.cesarwillymc.animeapp.ui.navigation.route

sealed class BottomAppBarRoute(path: String) : Route(path) {
    object Main : BottomAppBarRoute("main")
    object Gift : BottomAppBarRoute("gift")
    object Wishlist : BottomAppBarRoute("wishlist")
}
