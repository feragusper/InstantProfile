package com.feragusper.instantprofile.projects.viewmodel

import com.feragusper.instantprofile.commons.viewmodel.BaseViewModel
import com.feragusper.instantprofile.projects.domain.model.Project

abstract class ProjectListViewModel : BaseViewModel<ProjectListViewModel.State>() {
    sealed class State {
        data class Success(val projectList: List<Project>) : State()
        data class Error(val message: String) : State()
    }

    abstract fun fetchAllProjects()
}