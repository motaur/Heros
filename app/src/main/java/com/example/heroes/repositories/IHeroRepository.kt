package com.example.heroes.repositories

import com.example.heroes.models.HeroApiModel

interface IHeroRepository {
    suspend fun searchHeroByName(query: String): List<HeroApiModel>
    suspend fun getById(id: String) : HeroApiModel?
}