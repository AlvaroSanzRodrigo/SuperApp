package com.sanzsoftware.superapp.models

import com.sanzsoftware.superapp.api.RetroFitRepository

class CharacterViewModel {
    var retroFitRepository: RetroFitRepository? = RetroFitRepository.getInstance()
    fun getCharacters() = retroFitRepository?.getCharacters()
}