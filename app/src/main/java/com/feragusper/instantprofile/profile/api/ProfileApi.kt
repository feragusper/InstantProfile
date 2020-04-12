package com.feragusper.instantprofile.profile.api

import com.feragusper.instantprofile.profile.domain.model.Profile

interface ProfileApi {
    suspend fun fetchProfile(): Profile

}