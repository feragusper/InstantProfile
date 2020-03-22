package com.feragusper.instantprofile.commons.featureflag

interface FeatureFlagProvider {
    val priority: Int
    fun isFeatureEnabled(feature: Feature): Boolean
    fun hasFeature(feature: Feature): Boolean
}

interface RemoteFeatureFlagProvider {
    fun refreshFeatureFlags()
}

const val TEST_PRIORITY = 0
const val MAX_PRIORITY = 1
const val MEDIUM_PRIORITY = 2
const val MIN_PRIORITY = 3