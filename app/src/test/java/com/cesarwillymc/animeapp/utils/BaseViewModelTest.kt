package com.cesarwillymc.animeapp.utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Created by luis on 05-07-22.
 * luis.alamos@reign.cl
 *
 * Santiago, Chile.
 */
@ExperimentalCoroutinesApi
abstract class BaseViewModelTest : MockkTest() {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    class MainDispatcherRule(
        val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    ) : TestWatcher() {
        override fun starting(description: Description) {
            Dispatchers.setMain(testDispatcher)
        }
        override fun finished(description: Description) {
            Dispatchers.resetMain()
        }
    }
}
