package com.krayushkindev.allsoundsxml

import android.media.MediaPlayer
import android.os.Bundle
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.krayushkindev.allsoundsxml.adapter.SoundsAdapter
import com.krayushkindev.allsoundsxml.databinding.ActivityMainBinding
import com.krayushkindev.allsoundsxml.model.SoundModel
import com.krayushkindev.allsoundsxml.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var viewModel: MainViewModel? = null
    private var soundsAdapter: SoundsAdapter? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initSounds()
        initAdapter()
        setObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
        soundsAdapter = null
    }

    private fun initSounds() {
        viewModel?.initSounds()
    }

    private fun initAdapter() {
        soundsAdapter = SoundsAdapter().apply {
            onSoundClick = { sound: SoundModel ->
                playMedia(resId = sound.audioResId)
            }
        }

        binding.allSoundsList.apply {
            adapter = soundsAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }

    private fun playMedia(@RawRes resId: Int) {
        mediaPlayer?.let { mp->
            stopAndSetNullableMediaPlayer()
        }

        mediaPlayer = MediaPlayer.create(this, resId)?.apply {
            start()
            /*setOnCompletionListener {
                // TODO: @AndreiKrayushkin [15.12.2023] [backlog]{если что-то нужно, то запихнуть сюда}
            }*/
        }
    }

    private fun stopAndSetNullableMediaPlayer() {
        mediaPlayer?.let { mp ->
            mp.stop()
            mp.release()
        }
        mediaPlayer = null
    }

    private fun setObservers() {
        lifecycleScope.launch {
            viewModel?.allSounds
                ?.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)?.collect { allSounds ->
                    soundsAdapter?.initSounds(allSounds = allSounds)
                }
        }
    }
}