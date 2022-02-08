package com.example.heros.repositories.heroRepository

import com.example.heros.models.HeroUiModel

interface HeroRepository {
    fun sayHello() : String
    suspend fun getHeroList() : Array<HeroUiModel>
}