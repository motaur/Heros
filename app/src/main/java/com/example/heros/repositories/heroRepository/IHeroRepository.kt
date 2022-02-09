package com.example.heros.repositories.heroRepository

import com.example.heros.models.HeroApiModel

interface IHeroRepository {
    suspend fun searchHeroByName(query: String): List<HeroApiModel>
}