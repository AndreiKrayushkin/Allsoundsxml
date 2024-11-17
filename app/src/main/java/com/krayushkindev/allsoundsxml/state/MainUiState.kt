package com.krayushkindev.allsounds.state

import com.krayushkindev.allsounds.core.UiState
import com.krayushkindev.allsounds.state.model.SoundModel

data class MainUiState(
    val allSounds: List<SoundModel> = emptyList(),
    val id: Int = 0
) : UiState