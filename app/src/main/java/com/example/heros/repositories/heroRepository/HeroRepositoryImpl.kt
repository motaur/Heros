package com.example.heros.repositories.heroRepository

import com.example.heros.api.HeroesProvider
import com.example.heros.models.HeroApiModel

class HeroRepositoryImpl(private val provider: HeroesProvider) : IHeroRepository {
    override suspend fun searchHeroByName(query: String) : List<HeroApiModel> =
          provider.searchByName(query).results
}