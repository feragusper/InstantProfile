package com.feragusper.instantprofile.projects.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feragusper.instantprofile.commons.injection.qualifiers.ViewModelKey
import com.feragusper.instantprofile.commons.viewmodel.ViewModelFactory
import com.feragusper.instantprofile.projects.api.FirebaseDatabaseProjectApi
import com.feragusper.instantprofile.projects.api.ProjectApi
import com.feragusper.instantprofile.projects.interactor.FetchProjectListUseCase
import com.feragusper.instantprofile.projects.interactor.FetchProjectListUseCaseImpl
import com.feragusper.instantprofile.projects.repository.ProjectRepository
import com.feragusper.instantprofile.projects.repository.ProjectRepositoryImpl
import com.feragusper.instantprofile.projects.view.ProjectListFragment
import com.feragusper.instantprofile.projects.viewmodel.ProjectListViewModel
import com.feragusper.instantprofile.projects.viewmodel.ProjectListViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProjectListModule {

    @Provides
    @IntoMap
    @ViewModelKey(ProjectListViewModel::class)
    fun providesProjectListViewModelIntoMap(viewModelImpl: ProjectListViewModelImpl): ViewModel = viewModelImpl

    @Provides
    fun provideProjectListViewModel(fragment: ProjectListFragment, viewModelFactory: ViewModelFactory): ProjectListViewModel =
        ViewModelProvider(fragment, viewModelFactory)[ProjectListViewModel::class.java]

    @Provides
    fun provideFetchProjectListUseCase(useCase: FetchProjectListUseCaseImpl): FetchProjectListUseCase = useCase

    @Provides
    fun provideProjectRepository(repository: ProjectRepositoryImpl): ProjectRepository = repository

    @Provides
    fun provideProjectApi(): ProjectApi = FirebaseDatabaseProjectApi()
}
