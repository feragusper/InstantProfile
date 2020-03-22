package com.feragusper.instantprofile.commons.featureflag

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.feragusper.instantprofile.commons.featureflag.provider.FirebaseFeatureFlagProvider
import com.feragusper.instantprofile.commons.featureflag.provider.RuntimeFeatureFlagProvider
import com.feragusper.instantprofile.commons.featureflag.provider.StoreFeatureFlagProvider
import com.feragusper.instantprofile.commons.featureflag.provider.TestFeatureFlagProvider
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Check whether a feature should be enabled or not. Based on the priority of the different providers and if said
 * provider explicitly defines a value for that feature, the value of the flag is returned.
 */
object RuntimeBehavior {

    @VisibleForTesting
    internal val providers = CopyOnWriteArrayList<FeatureFlagProvider>()

    fun initialize(context: Context, isDebugBuild: Boolean) {
        if (isDebugBuild) {
            val runtimeFeatureFlagProvider = RuntimeFeatureFlagProvider(context)
            addProvider(runtimeFeatureFlagProvider)
            addProvider(TestFeatureFlagProvider)
            if (runtimeFeatureFlagProvider.isFeatureEnabled(TestSetting.DEBUG_FIREBASE)) {
                addProvider(FirebaseFeatureFlagProvider(true))
            }
        } else {
            addProvider(StoreFeatureFlagProvider())
            addProvider(FirebaseFeatureFlagProvider(false))
        }
    }

    fun isFeatureEnabled(feature: Feature): Boolean {
        return providers.filter { provider ->
                provider.hasFeature(feature)
            }
            .minBy(FeatureFlagProvider::priority)
            ?.isFeatureEnabled(feature)
            ?: feature.defaultValue
    }

    fun refreshFeatureFlags() {
        providers.filter { provider ->
                provider is RemoteFeatureFlagProvider
            }
            .forEach { remoteFeatureFlagProvider ->
                (remoteFeatureFlagProvider as RemoteFeatureFlagProvider).refreshFeatureFlags()
            }
    }

    fun addProvider(provider: FeatureFlagProvider) {
        providers.add(provider)
    }

    fun clearFeatureFlagProviders() = providers.clear()

    fun removeAllFeatureFlagProviders(priority: Int) = providers.removeAll(providers.filter { provider -> provider.priority == priority })
}