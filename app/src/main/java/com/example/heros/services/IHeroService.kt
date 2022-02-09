package com.example.heros.services

import com.example.heros.models.HeroUiModel

interface IHeroService {
    suspend fun searchHeroByName(query: String): List<HeroUiModel>
}