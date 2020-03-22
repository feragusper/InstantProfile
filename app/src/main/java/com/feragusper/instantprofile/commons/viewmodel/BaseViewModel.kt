package com.feragusper.instantprofile.commons.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<STATE>(private val viewLiveData: MediatorLiveData<STATE> = MediatorLiveData()) : ViewModel() {

    fun getState(): LiveData<STATE> = viewLiveData

    fun setState(state: STATE) {
        viewLiveData.postValue(state)
    }

}