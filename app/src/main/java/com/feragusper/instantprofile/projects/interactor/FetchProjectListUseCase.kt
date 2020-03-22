package com.feragusper.instantprofile.projects.interactor

import com.feragusper.instantprofile.projects.domain.model.Project

interface FetchProjectListUseCase {
    suspend fun fetchAll(): List<Project>
}