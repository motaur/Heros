package com.example.heroes.services

import com.example.heroes.models.HeroApiModel
import com.example.heroes.models.HeroUiModel
import com.example.heroes.repositories.IHeroRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class HeroService(private val heroRepository: IHeroRepository): IHeroService {

    override suspend fun searchHeroByName(query: String) : List<HeroUiModel> {
        return heroRepository.searchHeroByName(query).let { heroesList ->
            heroesList.map { adaptHeroModel(it) }
        }
    }

    override suspend fun getSuggestions(suggestionsIds: List<String>): List<HeroUiModel> {

        val suggestions = mutableListOf<HeroApiModel>()

        return coroutineScope {
            suggestionsIds.forEach {
                val result = async { heroRepository.getById(it) }
                result.await()?.let {
                    suggestions.add(it)
                }
            }
            return@coroutineScope suggestions.map { adaptHeroModel(it) }
        }
    }

    private fun adaptHeroModel(heroApiModel: HeroApiModel) : HeroUiModel {
        return HeroUiModel(
            heroApiModel.id,
            heroApiModel.name,
            heroApiModel.image.url,
            heroApiModel.biography,
            heroApiModel.appearance,
            heroApiModel.work,
            heroApiModel.connections
        )
    }
}