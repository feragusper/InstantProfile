package com.feragusper.instantprofile.profile.interactor

import com.feragusper.instantprofile.profile.domain.model.Profile

interface FetchProfileUseCase {
    suspend fun fetchProfile(): Profile
}