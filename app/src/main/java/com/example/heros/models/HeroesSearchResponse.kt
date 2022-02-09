package com.example.heros.models

import com.google.gson.annotations.SerializedName

data class HeroesSearchResponse(
    @field:SerializedName("results") val results: List<HeroApiModel>,
    @field:SerializedName("response") val response: String,
    @field:SerializedName("results-for") val resultsFor: String
)