package com.feragusper.instantprofile.commons.featureflag.provider

import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.featureflag.Feature
import com.feragusper.instantprofile.commons.featureflag.FeatureFlagProvider
import com.feragusper.instantprofile.commons.featureflag.MAX_PRIORITY
import com.feragusper.instantprofile.commons.featureflag.RemoteFeatureFlagProvider
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class FirebaseFeatureFlagProvider(private val isDevModeEnabled: Boolean) : FeatureFlagProvider, RemoteFeatureFlagProvider {
    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(1)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    override val priority: Int = MAX_PRIORITY

    override fun isFeatureEnabled(feature: Feature): Boolean = remoteConfig.getBoolean(feature.key)

    override fun hasFeature(feature: Feature): Boolean {
        return true
    }

    override fun refreshFeatureFlags() {
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
            }
        }
    }

    private fun getCacheExpirationSeconds(isDevModeEnabled: Boolean): Long = if (isDevModeEnabled) {
        CACHE_EXPIRATION_SECS_DEV
    } else {
        CACHE_EXPIRATION_SECS
    }

    companion object {
        const val CACHE_EXPIRATION_SECS = 1 * 60 * 60L
        const val CACHE_EXPIRATION_SECS_DEV = 1L
    }
}