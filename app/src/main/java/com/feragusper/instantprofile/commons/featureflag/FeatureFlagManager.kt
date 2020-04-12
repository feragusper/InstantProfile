package com.feragusper.instantprofile.commons.featureflag

import com.feragusper.instantprofile.commons.featureflag.configurations.FeatureFlagConfigurations
import com.feragusper.instantprofile.commons.featureflag.configurations.RemoteFeatureFlagConfigurations
import javax.inject.Inject

/**
 * Check whether a feature should be enabled or not. Based on the priority of the different providers and if said
 * provider explicitly defines a value for that feature, the value of the flag is returned.
 */
class FeatureFlagManager @Inject constructor(private val configurations: ArrayList<FeatureFlagConfigurations>) {

    fun isFeatureEnabled(feature: Feature): Boolean {
        return configurations.filter { provider ->
            provider.hasFeature(feature)
        }
            .minBy(FeatureFlagConfigurations::priority)
            ?.isFeatureEnabled(feature)
            ?: feature.defaultValue
    }

    suspend fun refreshFeatureFlags() {
        configurations.filter { provider ->
            provider is RemoteFeatureFlagConfigurations
        }
            .forEach { remoteFeatureFlagProvider ->
                (remoteFeatureFlagProvider as RemoteFeatureFlagConfigurations).refreshFeatureFlags()
            }
    }

}