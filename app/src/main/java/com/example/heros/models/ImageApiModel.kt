package com.example.heros.models

import com.google.gson.annotations.SerializedName

data class ImageApiModel(
    @field:SerializedName("url") val url: String
)