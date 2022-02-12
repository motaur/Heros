package com.example.heros.models

import com.google.gson.annotations.SerializedName

data class HeroApiModel (
    @field:SerializedName("id") val id: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("image") val image: ImageApiModel,
    @field:SerializedName("biography") val biography: Biography,
    @field:SerializedName("appearance") val appearance: Appearance,
    @field:SerializedName("work") val work: Work,
    @field:SerializedName("connections") val connections: Connections
)

data class Biography(
    @field:SerializedName("full-name") val fullName: String,
    @field:SerializedName("publisher") val publisher: String,
    @field:SerializedName("place-of-birth") val placeOfBirth: String
)

data class Appearance(
    @field:SerializedName("eye-color") val eyeColor: String,
    @field:SerializedName("gender") val gender: String,
    @field:SerializedName("hair-color") val hairColor: String,
    @field:SerializedName("weight") val weight: Array<String>
)

data class Work(
    @field:SerializedName("occupation") val occupation: String,
    @field:SerializedName("base") val base: String
)

data class Connections(
    @field:SerializedName("group-affiliation") val groupAffiliation: String,
    @field:SerializedName("relatives") val relatives: String
)