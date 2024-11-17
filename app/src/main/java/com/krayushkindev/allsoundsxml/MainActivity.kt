package com.krayushkindev.allsounds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krayushkindev.allsounds.ui.MainUi
import com.krayushkindev.allsounds.ui.theme.AllSoundsTheme
import com.krayushkindev.allsounds.ui.theme.Black
import com.krayushkindev.allsounds.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllSoundsTheme {
                val viewModel: MainViewModel = viewModel()
                val state = viewModel.uiState.collectAsState().value

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Black
                ) {
                    MainUi(
                        allSounds = state.allSounds
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AllSoundsTheme {
        MainUi(
            allSounds = emptyList()
        )
    }
}