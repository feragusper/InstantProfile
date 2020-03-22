package com.feragusper.instantprofile.commons.view

import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import com.feragusper.instantprofile.commons.extensions.observe
import com.feragusper.instantprofile.commons.viewmodel.BaseViewModel

interface BaseViewInterface<S, V> where V : BaseViewModel<S> {

    @LayoutRes
    fun getLayoutId(): Int

    fun getDomainViewModel(): V
    fun onStateChanged(state: S)

}

fun <T, S, V> T.initStateObservers() where V : BaseViewModel<S>, T : LifecycleOwner, T : BaseViewInterface<S, V> {
    observe(getDomainViewModel().getState()) { onStateChanged(it) }
}
