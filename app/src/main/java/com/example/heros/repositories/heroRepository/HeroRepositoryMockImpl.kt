package com.example.heros.repositories.heroRepository

import com.example.heros.models.HeroUiModel
import kotlinx.coroutines.delay

class HeroRepositoryMockImpl : HeroRepository {

    override fun sayHello() = "hello from $this"

    override suspend fun getHeroList() : Array<HeroUiModel> {
        delay(5000)
        return arrayOf(HeroUiModel("Batman"), HeroUiModel("Superman") )
    }
}