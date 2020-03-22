package com.feragusper.instantprofile.profile.viewmodel

import com.feragusper.instantprofile.profile.interactor.FetchProfileUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModelImpl @Inject constructor(
    private val fetchProfileUseCase: FetchProfileUseCase
) : ProfileViewModel() {

    override fun fetchProfile() {
        setState(State.Loading)
        GlobalScope.launch {
            try {
                val profile = fetchProfileUseCase.fetchProfile()
                setState(State.Success(profile))
            } catch (e: Exception) {
                setState(State.Error(e.message ?: "Unknown"))
            }
        }
    }
}
