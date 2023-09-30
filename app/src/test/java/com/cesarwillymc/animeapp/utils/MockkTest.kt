package com.cesarwillymc.animeapp.utils

import io.mockk.MockKAnnotations
import org.junit.Before

open class MockkTest {

    @Before
    fun initMocks() {
        MockKAnnotations.init(this)
    }
}
