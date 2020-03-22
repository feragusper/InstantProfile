package com.feragusper.instantprofile.profile.repository

import com.feragusper.instantprofile.profile.domain.model.Profile

interface ProfileRepository {
    suspend fun fetchProfile(): Profile
}