package com.example.heros.models

import com.google.gson.annotations.SerializedName

data class HeroApiModel (
    @field:SerializedName("id") val id: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("image") val image: ImageApiModel
)