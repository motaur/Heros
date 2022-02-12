package com.example.heros.services

import com.example.heros.Constants
import com.example.heros.models.HeroApiModel
import com.example.heros.models.HeroUiModel
import com.example.heros.repositories.heroRepository.IHeroRepository
import kotlinx.coroutines.*

class HeroService(private val heroRepository: IHeroRepository): IHeroService {

    override suspend fun searchHeroByName(query: String) : List<HeroUiModel> {
        return heroRepository.searchHeroByName(query).let { heroesList ->
            heroesList.map { adaptHeroModel(it) }
        }
    }

    override suspend fun getSuggestions(): List<HeroUiModel> {

        val suggestions = mutableListOf<HeroApiModel>()
        val suggestionsIds = listOf(Constants.SUGGESTIONS_ID_1 , Constants.SUGGESTIONS_ID_2, Constants.SUGGESTIONS_ID_3)

        return coroutineScope {
            suggestionsIds.forEach {
                suggestions.add(withContext(Dispatchers.Default) {
                    heroRepository.getById(it)
                })
            }
            return@coroutineScope suggestions.map { adaptHeroModel(it) }
        }
    }

    private fun adaptHeroModel(heroApiModel: HeroApiModel) : HeroUiModel {
        return HeroUiModel(heroApiModel.id, heroApiModel.name.trim(), heroApiModel.image.url)
    }
}