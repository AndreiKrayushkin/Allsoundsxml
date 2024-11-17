package com.krayushkindev.allsounds.state.model

import androidx.annotation.RawRes
import com.krayushkindev.allsounds.core.constant.Constants.ZERO_INT
import com.krayushkindev.allsounds.core.extension.empty

data class SoundModel(
    val id: Int = ZERO_INT,
    val audioName: String = String.empty(),
    @RawRes val audioResId: Int = ZERO_INT
)
