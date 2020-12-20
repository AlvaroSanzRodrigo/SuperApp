package com.sanzsoftware.superapp.api

import com.google.gson.annotations.SerializedName
import com.sanzsoftware.superapp.models.Character

class Data(@SerializedName("results") var results: List<Character>) {
}