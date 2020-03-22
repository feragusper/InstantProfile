package com.feragusper.instantprofile.commons

import com.feragusper.instantprofile.BuildConfig
import com.feragusper.instantprofile.commons.featureflag.RuntimeBehavior
import com.feragusper.instantprofile.commons.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        RuntimeBehavior.initialize(this, BuildConfig.DEBUG)
        RuntimeBehavior.refreshFeatureFlags()
    }

}