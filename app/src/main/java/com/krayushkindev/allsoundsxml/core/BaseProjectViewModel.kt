package com.krayushkindev.allsounds.core

import androidx.lifecycle.ViewModel
import com.krayushkindev.allsounds.core.resource.StringResource

abstract class BaseProjectViewModel : ViewModel() {
    protected open val stringResource: StringResource? = null
}