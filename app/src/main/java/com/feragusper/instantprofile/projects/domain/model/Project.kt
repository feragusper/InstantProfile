package com.feragusper.instantprofile.projects.domain.model

import com.feragusper.instantprofile.projects.domain.Technology

data class Project(
    val name: String,
    val logoFirebaseStorageUrl: String,
    val linkUrl: String,
    val description: String,
    val technologies: List<Technology>
)