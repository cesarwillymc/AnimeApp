package com.cesarwillymc.animeapp.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
object TestDispatcher {
    @OptIn(ExperimentalCoroutinesApi::class)
    val newTestDispatcher = UnconfinedTestDispatcher()
}
