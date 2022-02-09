package com.example.heros.repositories.heroRepository

import com.example.heros.models.HeroApiModel
import com.example.heros.models.ImageApiModel
import kotlinx.coroutines.delay

class HeroRepositoryMockImpl : IHeroRepository {

    override suspend fun searchHeroByName(query: String): List<HeroApiModel> {
        delay(5000)
        return listOf(
            HeroApiModel("1", "Batman", ImageApiModel("")),
            HeroApiModel("2", "Robin", ImageApiModel(""))
        )
    }
}