package com.krayushkindev.allsoundsxml.model

import androidx.annotation.RawRes
import com.krayushkindev.allsoundsxml.constant.Constants.ZERO_INT
import com.krayushkindev.allsoundsxml.extension.empty

data class SoundModel(
    val id: Int = ZERO_INT,
    val audioName: String = String.empty(),
    @RawRes val audioResId: Int = ZERO_INT
)
