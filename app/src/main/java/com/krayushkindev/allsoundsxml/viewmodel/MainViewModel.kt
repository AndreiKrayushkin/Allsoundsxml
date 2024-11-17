package com.krayushkindev.allsounds.viewmodel

import com.krayushkindev.allsounds.R
import com.krayushkindev.allsounds.core.BaseViewModel
import com.krayushkindev.allsounds.core.resource.StringResource
import com.krayushkindev.allsounds.state.MainUiState
import com.krayushkindev.allsounds.state.model.SoundModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    override val stringResource: StringResource
) : BaseViewModel<MainUiState>() {

    override val _uiState = MutableStateFlow(MainUiState())
    override val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                allSounds = listOf(
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_lion),
                        audioResId = R.raw.lev_rychit
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_kolokolchik),
                        audioResId = R.raw.kolokolchik
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_molotok),
                        audioResId = R.raw.molotok
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_cyplyata_pischat_ptenccy_na_ferme),
                        audioResId = R.raw.cyplyata_pischat_ptenccy_na_ferme
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_gusi_gagochut),
                        audioResId = R.raw.gusi_gagochut
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_avto_zavoditsia_i_uezjaet),
                        audioResId = R.raw.avto_zavoditsia_i_uezjaet
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_hrjukane_vizg_svinei),
                        audioResId = R.raw.hrjukane_vizg_svinei
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_lai_sobaki),
                        audioResId = R.raw.lai_sobaki
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_kot_myaukaet),
                        audioResId = R.raw.kot_myaukaet
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_mychanie_korovy),
                        audioResId = R.raw.mychanie_korovy
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_rzhanie_loshadi),
                        audioResId = R.raw.rzhanie_loshadi
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_kozel_i_koza_blejut),
                        audioResId = R.raw.kozel_i_koza_blejut
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_kryakaet_utka),
                        audioResId = R.raw.kryakaet_utka
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_kukushka),
                        audioResId = R.raw.kukushka
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_lyagushka),
                        audioResId = R.raw.lyagushka
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_petuh_krichit),
                        audioResId = R.raw.petuh_krichit
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_soroka),
                        audioResId = R.raw.soroka
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_mysh),
                        audioResId = R.raw.mysh
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_vorobi),
                        audioResId = R.raw.vorobi
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_fen),
                        audioResId = R.raw.fen
                    ),
                    SoundModel(
                        audioName = stringResource.getString(R.string.name_chasy_tikajut),
                        audioResId = R.raw.chasy_tikajut
                    ),
                )
            )
        }
    }
}