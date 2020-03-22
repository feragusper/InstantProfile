package com.feragusper.instantprofile.projects.repository

import com.feragusper.instantprofile.projects.domain.model.Project

interface ProjectRepository {
    suspend fun fetchProjects(): List<Project>
}