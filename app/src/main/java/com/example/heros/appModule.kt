package com.example.heros

import com.example.heros.api.HeroesProvider
import com.example.heros.repositories.heroRepository.HeroRepositoryImpl
import com.example.heros.repositories.heroRepository.IHeroRepository
import com.example.heros.services.HeroService
import com.example.heros.services.IHeroService
import com.example.heros.ui.heroList.HeroListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModule = module {

    single { HeroesProvider.create() }

    factory<IHeroRepository> { HeroRepositoryImpl(get()) } //HeroRepositoryMockImpl()
    factory<IHeroService> { HeroService(get()) }

    viewModel { HeroListViewModel(get()) }
}