package com.feragusper.instantprofile.commons.injection.scopes

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Scope
@Retention(RUNTIME)
annotation class PerApplication