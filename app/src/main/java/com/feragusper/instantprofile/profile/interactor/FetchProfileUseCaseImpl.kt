package com.feragusper.instantprofile.profile.interactor

import com.feragusper.instantprofile.profile.domain.model.Profile
import com.feragusper.instantprofile.profile.repository.ProfileRepository
import javax.inject.Inject

class FetchProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
) : FetchProfileUseCase {

    override suspend fun fetchProfile(): Profile {
        return repository.fetchProfile()
    }
}