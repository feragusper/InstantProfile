package com.feragusper.instantprofile.profile.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feragusper.instantprofile.commons.injection.qualifiers.ViewModelKey
import com.feragusper.instantprofile.commons.viewmodel.ViewModelFactory
import com.feragusper.instantprofile.profile.interactor.FetchProfileUseCase
import com.feragusper.instantprofile.profile.interactor.FetchProfileUseCaseImpl
import com.feragusper.instantprofile.profile.repository.ProfileRepository
import com.feragusper.instantprofile.profile.repository.ProfileRepositoryImpl
import com.feragusper.instantprofile.profile.view.ProfileFragment
import com.feragusper.instantprofile.profile.viewmodel.ProfileViewModel
import com.feragusper.instantprofile.profile.viewmodel.ProfileViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProfileModule {

    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun providesProfileViewModelIntoMap(viewModelImpl: ProfileViewModelImpl): ViewModel = viewModelImpl

    @Provides
    fun provideProfileViewModel(fragment: ProfileFragment, viewModelFactory: ViewModelFactory): ProfileViewModel =
        ViewModelProvider(fragment, viewModelFactory)[ProfileViewModel::class.java]

    @Provides
    fun provideFetchProfileUseCase(useCase: FetchProfileUseCaseImpl): FetchProfileUseCase = useCase

    @Provides
    fun provideProfileRepository(repository: ProfileRepositoryImpl): ProfileRepository = repository
}
