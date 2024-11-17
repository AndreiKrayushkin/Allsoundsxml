package com.krayushkindev.allsounds.ui

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.krayushkindev.allsounds.R
import com.krayushkindev.allsounds.state.model.SoundModel
import com.krayushkindev.allsounds.ui.theme.Black
import com.krayushkindev.allsounds.ui.theme.LightGray
import com.krayushkindev.allsounds.ui.widget.ClickAnimateBlock

@Composable
fun MainUi(
    allSounds: List<SoundModel>
) {
    val mediaPlayer = remember { mutableStateOf<MediaPlayer?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxHeight(0.9f)
        ) {
            items(allSounds) { soundModel ->
                SoundItem(
                    soundModel = soundModel,
                    valueOfProgress = 10,
                    onSoundItemClick = {
                        playMedia(
                            mediaPlayer = mediaPlayer,
                            context = context,
                            resId = soundModel.audioResId
                        )
                    }
                )
            }
        }

        ClickAnimateBlock(onFinishClick = {
            stopAndSetNullableMediaPlayer(mediaPlayer)
        }) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .height(60.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = LightGray),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.stop_all_sounds),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

private fun playMedia(mediaPlayer: MutableState<MediaPlayer?>, context: Context, @RawRes resId: Int) {
    mediaPlayer.value?.let { mp->
        stopAndSetNullableMediaPlayer(mediaPlayer)
    }

    mediaPlayer.value = MediaPlayer.create(context, resId)?.apply {
        start()
        /*setOnCompletionListener {
            // TODO: @AndreiKrayushkin [15.12.2023] [backlog]{если что-то нужно после завершения звука, то запихнуть сюда}
        }*/
    }
}

private fun stopAndSetNullableMediaPlayer(mediaPlayer: MutableState<MediaPlayer?>) {
    mediaPlayer.value?.let { mp ->
        mp.stop()
        mp.release()
    }
    mediaPlayer.value = null
}

@Composable
@Preview
fun PreviewMainUi() {
    MainUi(
        allSounds = listOf(
            SoundModel(
                id = 10,
                audioName = "Лев",
                audioResId = R.raw.lev_rychit
            ),
            SoundModel(
                id = 1,
                audioName = "Колокольчик",
                audioResId = R.raw.kolokolchik
            ),
            SoundModel(
                id = 1,
                audioName = "Молоток",
                audioResId = R.raw.molotok
            )
        )
    )
}