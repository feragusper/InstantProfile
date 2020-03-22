package com.feragusper.instantprofile.commons.featureflag.provider

import com.feragusper.instantprofile.commons.featureflag.Feature
import com.feragusper.instantprofile.commons.featureflag.FeatureFlagProvider
import com.feragusper.instantprofile.commons.featureflag.TEST_PRIORITY

/**
 * For use during unit/instrumentation tests, allows to dynamically enable/disable features
 * during automated tests
 */
object TestFeatureFlagProvider : FeatureFlagProvider {

    private val features = HashMap<Feature, Boolean>()

    override val priority = TEST_PRIORITY

    override fun isFeatureEnabled(feature: Feature): Boolean = features[feature]!!

    override fun hasFeature(feature: Feature): Boolean = features.containsKey(feature)

    fun setFeatureEnabled(feature: Feature, enabled: Boolean) = features.put(feature, enabled)

    fun clearFeatures() = features.clear()
}