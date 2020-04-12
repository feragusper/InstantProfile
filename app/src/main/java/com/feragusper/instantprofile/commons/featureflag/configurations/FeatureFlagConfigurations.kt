package com.feragusper.instantprofile.commons.featureflag.configurations

import com.feragusper.instantprofile.commons.featureflag.Feature

interface FeatureFlagConfigurations {
    val priority: Int
    fun isFeatureEnabled(feature: Feature): Boolean
    fun hasFeature(feature: Feature): Boolean
}

interface RemoteFeatureFlagConfigurations {
    suspend fun refreshFeatureFlags(): Boolean
}

const val TEST_PRIORITY = 0
const val MAX_PRIORITY = 1
const val MEDIUM_PRIORITY = 2
const val MIN_PRIORITY = 3