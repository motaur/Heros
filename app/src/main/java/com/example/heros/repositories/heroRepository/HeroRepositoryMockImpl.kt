package com.example.heros.repositories.heroRepository

import com.example.heros.models.HeroUiModel

class HeroRepositoryMockImpl : HeroRepository {

    override fun sayHello() = "hello from $this"

    override suspend fun getHeroList() : List<HeroUiModel> {
        return listOf(HeroUiModel("Batman"), HeroUiModel("Superman") )
    }
}