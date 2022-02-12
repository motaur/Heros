package com.example.heros.repositories.heroRepository

import com.example.heros.api.HeroProvider
import com.example.heros.models.HeroApiModel

class HeroRepositoryImpl(private val provider: HeroProvider) : IHeroRepository {

    override suspend fun searchHeroByName(query: String) : List<HeroApiModel> =
          provider.searchByName(query).results ?: emptyList()

    override suspend fun getById(id: String): HeroApiModel =
        provider.getById(id)
}