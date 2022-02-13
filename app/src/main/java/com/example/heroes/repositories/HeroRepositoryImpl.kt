package com.example.heroes.repositories

import com.example.heroes.api.RetrofitHeroProvider
import com.example.heroes.models.HeroApiModel

class HeroRepositoryImpl(private val providerRetrofit: RetrofitHeroProvider) : IHeroRepository {

    override suspend fun searchHeroByName(query: String) : List<HeroApiModel> =
          providerRetrofit.searchByName(query).results ?: emptyList()

    override suspend fun getById(id: String): HeroApiModel? =
        providerRetrofit.getById(id)
}