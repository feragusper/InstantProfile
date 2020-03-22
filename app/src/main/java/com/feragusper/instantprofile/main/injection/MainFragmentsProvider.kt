package com.feragusper.instantprofile.main.injection

import com.feragusper.instantprofile.profile.injection.ProfileModule
import com.feragusper.instantprofile.profile.view.ProfileFragment
import com.feragusper.instantprofile.projects.injection.ProjectListModule
import com.feragusper.instantprofile.projects.view.ProjectListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsProvider {

    @ContributesAndroidInjector(modules = [ProjectListModule::class])
    abstract fun providesProjectListFragment(): ProjectListFragment

    @ContributesAndroidInjector(modules = [ProfileModule::class])
    abstract fun providesProfileFragment(): ProfileFragment
}
