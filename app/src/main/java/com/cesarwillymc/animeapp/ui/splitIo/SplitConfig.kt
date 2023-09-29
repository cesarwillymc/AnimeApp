package com.cesarwillymc.animeapp.ui.splitIo

import android.content.Context
import android.os.Build
import android.util.Log
import com.cesarwillymc.animeapp.BuildConfig
import io.split.android.client.SplitClient
import io.split.android.client.SplitClientConfig
import io.split.android.client.SplitFactoryBuilder
import io.split.android.client.api.Key
import io.split.android.client.events.SplitEvent
import io.split.android.client.events.SplitEventTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SplitConfig constructor(
    private val context: Context
) {
    private val matchingKey = Build.MODEL + Build.MANUFACTURER
    private val key = Key(matchingKey, null)
    init {
        initializeSplitClient()
        checkFeatureFlag()
    }

    private fun initializeSplitClient() {
        try {
            val splitFactory = SplitFactoryBuilder.build(
                BuildConfig.split_io_key,
                key,
                createSplitClientConfig(),
                context
            )
            splitClient = splitFactory.client()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createSplitClientConfig(): SplitClientConfig {
        // Customize and build the SplitClientConfig here if needed
        return SplitClientConfig.builder()
            .build()
    }

    private fun checkFeatureFlag() {
        splitClient?.on(SplitEvent.SDK_READY, object : SplitEventTask() {
            override fun onPostExecution(client: SplitClient) {
                val treatment = client.getTreatment(BuildConfig.feature_flag)
                _flagGifActive.update { treatment == "on" }
            }

            override fun onPostExecutionView(client: SplitClient) {
                // Do some UI work if needed
            }
        })
    }

    companion object {
        private val _flagGifActive = MutableStateFlow(false)
        val flagGifActive: StateFlow<Boolean> = _flagGifActive.asStateFlow()
        var splitClient: SplitClient? = null
    }
}