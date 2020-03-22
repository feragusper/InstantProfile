package com.feragusper.instantprofile.profile.viewmodel

import com.feragusper.instantprofile.commons.viewmodel.BaseViewModel
import com.feragusper.instantprofile.profile.domain.model.Profile

abstract class ProfileViewModel : BaseViewModel<ProfileViewModel.State>() {
    sealed class State {
        object Loading : State()
        data class Success(val profile: Profile) : State()
        data class Error(val message: String) : State()
    }

    abstract fun fetchProfile()
}