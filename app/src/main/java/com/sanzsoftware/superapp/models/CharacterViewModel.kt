package com.sanzsoftware.superapp.models

import androidx.lifecycle.ViewModel
import com.sanzsoftware.superapp.api.RetroFitRepository

class CharacterViewModel: ViewModel() {
    var retroFitRepository: RetroFitRepository? = RetroFitRepository.getInstance()
    fun getCharacters() = retroFitRepository?.getCharacters()
}