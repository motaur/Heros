package com.example.heros.repositories

import com.example.heros.models.HeroApiModel

interface IHeroRepository {
    suspend fun searchHeroByName(query: String): List<HeroApiModel>
    suspend fun getById(id: String) : HeroApiModel?
}