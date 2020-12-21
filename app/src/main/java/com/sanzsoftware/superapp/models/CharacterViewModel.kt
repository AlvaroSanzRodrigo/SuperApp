package com.sanzsoftware.superapp.models

import androidx.lifecycle.ViewModel
import com.sanzsoftware.superapp.api.RetroFitRepository
import org.jetbrains.anko.doAsync

class CharacterViewModel: ViewModel() {
    var retroFitRepository: RetroFitRepository? = RetroFitRepository.getInstance()


     fun getCharacters() = retroFitRepository?.getCharacters()
}