package com.example.heros.services

import com.example.heros.models.HeroUiModel
import com.example.heros.repositories.heroRepository.IHeroRepository

class HeroService(private val heroRepository: IHeroRepository): IHeroService {

    override suspend fun searchHeroByName(query: String) : List<HeroUiModel> {
        return heroRepository.searchHeroByName(query).let { heroesList ->
            heroesList.map {
                HeroUiModel(it.id, it.name, it.image.url)
            }
        }
    }
}