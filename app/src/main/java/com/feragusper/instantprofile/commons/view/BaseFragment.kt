package com.feragusper.instantprofile.commons.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.feragusper.instantprofile.commons.viewmodel.BaseViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VIEWMODEL, STATE> : BaseViewInterface<STATE, VIEWMODEL>, DaggerFragment() where VIEWMODEL : BaseViewModel<STATE> {

    @Inject
    lateinit var viewModel: VIEWMODEL

    override fun getDomainViewModel(): VIEWMODEL {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStateObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

}
