package com.krayushkindev.allsounds.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.krayushkindev.allsounds.core.constant.Constants.ZERO_FLOAT
import com.krayushkindev.allsounds.state.model.SoundModel
import com.krayushkindev.allsounds.ui.theme.Black
import com.krayushkindev.allsounds.ui.theme.Gray1
import com.krayushkindev.allsounds.ui.theme.LightGray
import com.krayushkindev.allsounds.ui.widget.ClickAnimateBlock

private const val MAX_PROGRESS = 100

@Composable
fun SoundItem(
    soundModel: SoundModel,
    valueOfProgress: Int,
    onSoundItemClick: () -> Unit
) {
    var currentProgress by remember { mutableFloatStateOf(ZERO_FLOAT) }

    LaunchedEffect(valueOfProgress) {
        currentProgress = valueOfProgress.toFloat()
    }

    ClickAnimateBlock(
        onFinishClick = { onSoundItemClick() }
    ) {
        Canvas(modifier = Modifier
            .padding(5.dp)
            .height(40.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Black,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(color = LightGray)) {

            /* TODO: @AndreiKrayushkin [12.03.2024] {not working} */
//            drawRoundRect(
//                brush = Brush.verticalGradient(
//                    listOf(
//                        Black,
//                        Black
//                    )
//                ),
//                size = Size(
//                    width = currentProgress,
//                    height = size.height
//                )
//            )
        }
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = soundModel.audioName
            )
        }
    }
}

@Preview(name = "SoundItem")
@Composable
private fun PreviewSoundItem() {
    SoundItem(
        soundModel = SoundModel(
            audioName = "ololo"
        ),
        onSoundItemClick = {},
        valueOfProgress = 1000
    )
}