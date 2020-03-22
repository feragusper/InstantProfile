package com.feragusper.instantprofile.commons.injection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.feragusper.instantprofile.commons.App
import com.feragusper.instantprofile.commons.injection.qualifiers.AppContext
import com.feragusper.instantprofile.commons.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    @AppContext
    abstract fun provideContext(app: App): Context

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
