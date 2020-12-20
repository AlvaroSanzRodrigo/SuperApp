package com.sanzsoftware.superapp.api

import com.google.gson.annotations.SerializedName
import com.sanzsoftware.superapp.models.Character

data class CharacterResponse(
    @SerializedName("status") var status: String,
    @SerializedName("results") var resuts: List<Character>
) {
}