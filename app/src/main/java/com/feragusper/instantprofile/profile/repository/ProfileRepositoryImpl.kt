package com.feragusper.instantprofile.profile.repository

import com.feragusper.instantprofile.profile.api.ProfileApi
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val api: ProfileApi) : ProfileRepository {
    override suspend fun fetchProfile() = api.fetchProfile()
}