package com.example.heros.repositories.heroRepository

import com.example.heros.models.*
import kotlinx.coroutines.delay
import kotlin.random.Random

class HeroRepositoryMockImpl : IHeroRepository {

    override suspend fun searchHeroByName(query: String): List<HeroApiModel> {
        delay(5000)
        return listOf(
            getMockHero(), getMockHero(), getMockHero(), getMockHero()
        )
    }

    override suspend fun getById(id: String): HeroApiModel {
        delay(Random.nextLong(5000))
        return getMockHero(id)
    }

    private fun getMockHero(id: String? = null) : HeroApiModel {

        val mockId : String = id ?: Random.nextInt(1000).toString()

       return HeroApiModel(
            mockId, "Hero $mockId", ImageApiModel(""),
            Biography("Noob noob $mockId", "Adult Swim", "Unknown" ),
            Appearance("black", "male", "bold", arrayOf("30 kg")),
            Work("Janitor ", "Vindicators Space Base"),
            Connections("Vindicators", "Vindicators")
        )
    }
}