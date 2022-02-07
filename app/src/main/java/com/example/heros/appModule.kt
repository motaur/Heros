package com.example.heros

import com.example.heros.repositories.heroRepository.HeroRepository
import com.example.heros.repositories.heroRepository.HeroRepositoryMockImpl
import com.example.heros.ui.heroList.HeroListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModule = module {

    // single instance of HelloRepository
    single<HeroRepository> { HeroRepositoryMockImpl() }
//
//    // Simple Presenter Factory
//    factory { MySimplePresenter(get()) }


    viewModel { HeroListViewModel(get()) }
}