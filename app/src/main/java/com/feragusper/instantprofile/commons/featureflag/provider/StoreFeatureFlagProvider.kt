package com.feragusper.instantprofile.commons.featureflag.provider

import com.feragusper.instantprofile.commons.featureflag.*

class StoreFeatureFlagProvider : FeatureFlagProvider {

    override val priority = MIN_PRIORITY

    @Suppress("ComplexMethod")
    override fun isFeatureEnabled(feature: Feature): Boolean {
        return if (feature is FeatureFlag) {
            // No "else" branch here -> choosing the default option for release must be an explicit choice
            true
        } else {
            // TestSettings should never be shipped to users
            when (feature as TestSetting) {
                else -> false
            }
        }
    }

    override fun hasFeature(feature: Feature): Boolean = true
}