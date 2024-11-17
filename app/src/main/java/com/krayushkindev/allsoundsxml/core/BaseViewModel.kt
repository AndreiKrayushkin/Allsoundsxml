package com.krayushkindev.allsounds.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<UiState : com.krayushkindev.allsounds.core.UiState> : BaseProjectViewModel()
{
    protected abstract val _uiState: MutableStateFlow<out UiState>
    abstract val uiState: StateFlow<UiState>
}