package com.example.heros.repositories

import com.example.heros.api.RetrofitHeroProvider
import com.example.heros.models.HeroApiModel

class HeroRepositoryImpl(private val providerRetrofit: RetrofitHeroProvider) : IHeroRepository {

    override suspend fun searchHeroByName(query: String) : List<HeroApiModel> =
          providerRetrofit.searchByName(query).results ?: emptyList()

    override suspend fun getById(id: String): HeroApiModel? =
        providerRetrofit.getById(id)
}