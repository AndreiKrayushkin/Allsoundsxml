package com.krayushkindev.allsoundsxml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krayushkindev.allsoundsxml.R
import com.krayushkindev.allsoundsxml.extension.empty
import com.krayushkindev.allsoundsxml.model.SoundModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel(), ViewModelProvider.Factory {
    private val _allSounds = MutableStateFlow(emptyList<SoundModel>())
    val allSounds: StateFlow<List<SoundModel>> = _allSounds.asStateFlow()

    fun initSounds() {
        _allSounds.value = listOf(
            SoundModel(
                id = 0,
                audioName = "vodka",
                audioResId = R.raw.vodka
            )
        )
    }
}