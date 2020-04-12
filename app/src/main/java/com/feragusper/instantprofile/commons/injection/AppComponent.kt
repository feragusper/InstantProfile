package com.feragusper.instantprofile.commons.injection

import com.feragusper.instantprofile.commons.App
import com.feragusper.instantprofile.commons.featureflag.injection.FeatureFlagModule
import com.feragusper.instantprofile.commons.injection.scopes.PerApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    FeatureFlagModule::class,
    ActivityProvider::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
