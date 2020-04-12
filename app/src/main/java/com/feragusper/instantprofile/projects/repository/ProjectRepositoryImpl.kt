package com.feragusper.instantprofile.projects.repository

import com.feragusper.instantprofile.projects.api.ProjectApi
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(val api: ProjectApi) : ProjectRepository {
    override suspend fun fetchProjects() = api.fetchProjectList()
}
