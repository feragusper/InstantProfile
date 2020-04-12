package com.feragusper.instantprofile.commons.featureflag.injection

import android.content.Context
import com.feragusper.instantprofile.commons.featureflag.configurations.FeatureFlagConfigurations
import com.feragusper.instantprofile.commons.featureflag.configurations.FeatureFlagConfigurationsProvider
import com.feragusper.instantprofile.commons.injection.qualifiers.AppContext
import dagger.Module
import dagger.Provides

@Module
class FeatureFlagModule {

    @Provides
    fun providesFeatureFlagConfigurations(@AppContext context: Context): ArrayList<FeatureFlagConfigurations> {
        return FeatureFlagConfigurationsProvider.createConfigurationsList(context)
    }

}