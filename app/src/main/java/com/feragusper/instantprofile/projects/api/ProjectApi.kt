package com.feragusper.instantprofile.projects.api

import com.feragusper.instantprofile.projects.domain.model.Project

interface ProjectApi {
    suspend fun fetchProjectList(): List<Project>

}