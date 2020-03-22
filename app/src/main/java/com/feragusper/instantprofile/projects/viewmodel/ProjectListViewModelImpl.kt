package com.feragusper.instantprofile.projects.viewmodel

import com.feragusper.instantprofile.projects.interactor.FetchProjectListUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectListViewModelImpl @Inject constructor(
    private val fetchProjectListUseCase: FetchProjectListUseCase
) : ProjectListViewModel() {

    override fun fetchAllProjects() {
        GlobalScope.launch {
            try {
                val projectList = fetchProjectListUseCase.fetchAll()
                setState(State.Success(projectList))
            } catch (e: Exception) {
                setState(State.Error(e.message ?: "Unknown"))
            }
        }
    }
}
