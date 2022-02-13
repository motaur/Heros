package com.example.heroes.models

import java.io.Serializable

data class HeroUiModel(
    val id: String,
    val name: String,
    val picture: String,
    val biography: Biography,
    val appearance: Appearance,
    val work: Work,
    val connections: Connections) : Serializable