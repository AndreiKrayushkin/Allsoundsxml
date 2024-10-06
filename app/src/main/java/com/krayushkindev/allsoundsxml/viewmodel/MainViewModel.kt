package com.krayushkindev.allsoundsxml.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.krayushkindev.allsoundsxml.R
import com.krayushkindev.allsoundsxml.model.SoundModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(), ViewModelProvider.Factory {
    private val _allSounds = MutableStateFlow(emptyList<SoundModel>())
    val allSounds: StateFlow<List<SoundModel>> = _allSounds.asStateFlow()

    fun initSounds() {
        _allSounds.value = listOf(
            SoundModel(
                id = 10,
                audioName = "Чайник",
                audioResId = R.raw.zvuk_kipjaschego_chajnika
            ),
            SoundModel(
                id = 1,
                audioName = "Ребенок плачет",
                audioResId = R.raw.rebenok_plachit
            ),
            SoundModel(
                id = 1,
                audioName = "Фен",
                audioResId = R.raw.zvuk_rabotayuschego_fena
            ),
            SoundModel(
                id = 1,
                audioName = "Пылесос",
                audioResId = R.raw.pyilesos_rabotaet_uborka
            ),
            SoundModel(
                id = 3,
                audioName = "Дождь",
                audioResId = R.raw.prolivnoy_dojd
            ),

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
            ),
            SoundModel(
                id = 1,
                audioName = "Цыплята",
                audioResId = R.raw.cyplyata_pischat_ptenccy_na_ferme
            ),
            SoundModel(
                id = 3,
                audioName = "Гуси",
                audioResId = R.raw.gusi_gagochut
            ),
            SoundModel(
                id = 3,
                audioName = "Автомобиль",
                audioResId = R.raw.avto_zavoditsia_i_uezjaet
            ),
            SoundModel(
                id = 4,
                audioName = "Свинья",
                audioResId = R.raw.hrjukane_vizg_svinei
            ),
            SoundModel(
                id = 9,
                audioName = "Собака",
                audioResId = R.raw.lai_sobaki
            ),
            SoundModel(
                id = 5,
                audioName = "Кот",
                audioResId = R.raw.kot_myaukaet
            ),
            SoundModel(
                id = 12,
                audioName = "Корова",
                audioResId = R.raw.mychanie_korovy
            ),
            SoundModel(
                id = 15,
                audioName = "Лошадь",
                audioResId = R.raw.rzhanie_loshadi
            ),
            SoundModel(
                id = 6,
                audioName = "Козел",
                audioResId = R.raw.kozel_i_koza_blejut
            ),
            SoundModel(
                id = 7,
                audioName = "Утка",
                audioResId = R.raw.kryakaet_utka
            ),
            SoundModel(
                id = 8,
                audioName = "Кукушка",
                audioResId = R.raw.kukushka
            ),


            SoundModel(
                id = 11,
                audioName = "Лягушка",
                audioResId = R.raw.lyagushka
            ),


            SoundModel(
                id = 14,
                audioName = "Петух",
                audioResId = R.raw.petuh_krichit
            ),

            SoundModel(
                id = 16,
                audioName = "Сорока",
                audioResId = R.raw.soroka
            ),
            SoundModel(
                id = 13,
                audioName = "Мышь",
                audioResId = R.raw.mysh
            ),
            SoundModel(
                id = 17,
                audioName = "Воробьи",
                audioResId = R.raw.vorobi
            ),
            SoundModel(
                id = 2,
                audioName = "Фен",
                audioResId = R.raw.fen
            ),
            SoundModel(
                id = 0,
                audioName = "Часы",
                audioResId = R.raw.chasy_tikajut
            ),
        )
    }


    val scope = viewModelScope
    var job: Job? = null


    fun startTestFun() {
        launchOnDefault {
            fun1()
            fun2()
        }
    }

    private suspend fun fun1() {
        job?.join()
        job = scope.launch(Dispatchers.Default, CoroutineStart.DEFAULT) {
            for (i in 0..10) {
                Log.e("AndreiKrayushkin", "fun1: $i")
                delay(500)
            }
        }
    }

    private suspend fun fun2() {
        job?.join()
        job = scope.launch(Dispatchers.Default, CoroutineStart.DEFAULT) {
            for (i in 0..10) {
                Log.e("AndreiKrayushkin", "fun2: $i")
                delay(300)
            }
        }
    }

    suspend fun one(): Int {
        delay(200)
        return 12
    }
}

fun ViewModel.launchOnDefault(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launchOn(Dispatchers.Default, start, block)
}

private fun ViewModel.launchOn(
    dispatcher: CoroutineDispatcher,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(dispatcher, start, block)
}