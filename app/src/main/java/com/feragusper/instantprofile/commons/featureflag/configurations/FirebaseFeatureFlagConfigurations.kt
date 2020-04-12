package com.feragusper.instantprofile.commons.featureflag.configurations

import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.featureflag.Feature
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseFeatureFlagConfigurations : FeatureFlagConfigurations, RemoteFeatureFlagConfigurations {
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

    override suspend fun refreshFeatureFlags() = suspendCoroutine<Boolean> { cont ->
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            cont.resume(task.isSuccessful)
        }
    }
}