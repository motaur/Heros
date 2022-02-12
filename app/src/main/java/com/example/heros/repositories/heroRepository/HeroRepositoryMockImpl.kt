package com.example.heros.repositories.heroRepository

import com.example.heros.models.HeroApiModel
import com.example.heros.models.ImageApiModel
import kotlinx.coroutines.delay
import kotlin.random.Random

class HeroRepositoryMockImpl : IHeroRepository {

    override suspend fun searchHeroByName(query: String): List<HeroApiModel> {
        delay(5000)
        return listOf(
            HeroApiModel("1", "Batman", ImageApiModel("")),
            HeroApiModel("2", "Robin", ImageApiModel("")),
            HeroApiModel("3", "Spider-Man", ImageApiModel(""))
        )
    }

    override suspend fun getById(id: String): HeroApiModel {
        delay(Random.nextLong(5000))
        return HeroApiModel(
            id,
            "Hero $id",
            ImageApiModel(""))
    }
}