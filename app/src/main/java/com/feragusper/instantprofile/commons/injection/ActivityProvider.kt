package com.feragusper.instantprofile.commons.injection

import com.feragusper.instantprofile.commons.injection.scopes.PerActivity
import com.feragusper.instantprofile.main.injection.MainFragmentsProvider
import com.feragusper.instantprofile.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityProvider {
    @PerActivity
    @ContributesAndroidInjector(modules = [MainFragmentsProvider::class])
    abstract fun bindMainActivity(): MainActivity
}