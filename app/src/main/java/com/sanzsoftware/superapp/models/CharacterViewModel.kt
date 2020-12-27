package com.sanzsoftware.superapp.models

import com.sanzsoftware.superapp.api.RetroFitRepository

class CharacterViewModel(val url: String, val search: String?) {
    var retroFitRepository: RetroFitRepository? = RetroFitRepository.getInstance()
    fun getCharacters() = retroFitRepository?.getCharacters(url, search)
}