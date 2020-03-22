package com.feragusper.instantprofile.projects.interactor

import com.feragusper.instantprofile.projects.domain.model.Project
import com.feragusper.instantprofile.projects.repository.ProjectRepository
import javax.inject.Inject

class FetchProjectListUseCaseImpl @Inject constructor(
    private val repository: ProjectRepository
) : FetchProjectListUseCase {

    override suspend fun fetchAll(): List<Project> {
        return repository.fetchProjects()
    }
}