package com.feragusper.instantprofile.commons.featureflag.configurations

import android.content.Context

object FeatureFlagConfigurationsProvider {

    fun createConfigurationsList(context: Context) = arrayListOf(
        RuntimeFeatureFlagConfigurations(context),
        TestFeatureFlagConfigurations,
        FirebaseFeatureFlagConfigurations()
    )
}
