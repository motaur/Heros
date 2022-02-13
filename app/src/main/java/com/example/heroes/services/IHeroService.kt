package com.example.heroes.services

import com.example.heroes.models.HeroUiModel

interface IHeroService {
    suspend fun searchHeroByName(query: String): List<HeroUiModel>
    suspend fun getSuggestions(suggestionsIds: List<String>): List<HeroUiModel>
}