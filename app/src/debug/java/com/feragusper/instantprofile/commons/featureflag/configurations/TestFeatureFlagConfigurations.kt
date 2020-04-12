package com.feragusper.instantprofile.commons.featureflag.configurations

import com.feragusper.instantprofile.commons.featureflag.Feature

/**
 * For use during unit/instrumentation tests, allows to dynamically enable/disable features
 * during automated tests
 */
object TestFeatureFlagConfigurations : FeatureFlagConfigurations {

    private val features = HashMap<Feature, Boolean>()

    override val priority = TEST_PRIORITY

    override fun isFeatureEnabled(feature: Feature): Boolean = features[feature]!!

    override fun hasFeature(feature: Feature): Boolean = features.containsKey(feature)

    fun setFeatureEnabled(feature: Feature, enabled: Boolean) = features.put(feature, enabled)

    fun clearFeatures() = features.clear()
}