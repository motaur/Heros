package com.example.heros

import com.example.heros.repositories.HelloRepository
import com.example.heros.repositories.HeroRepositoryImpl
import com.example.heros.ui.heroList.HeroListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModule = module {

    // single instance of HelloRepository
    single<HelloRepository> { HeroRepositoryImpl() }
//
//    // Simple Presenter Factory
//    factory { MySimplePresenter(get()) }


    viewModel { HeroListViewModel() }
}