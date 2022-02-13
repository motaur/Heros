package com.example.heroes

import com.example.heroes.api.RetrofitHeroProvider
import com.example.heroes.helpers.NetworkHelper
import com.example.heroes.repositories.HeroRepositoryImpl
import com.example.heroes.repositories.HeroRepositoryMockImpl
import com.example.heroes.repositories.IHeroRepository
import com.example.heroes.services.HeroService
import com.example.heroes.services.IHeroService
import com.example.heroes.ui.heroDetails.HeroDetailsViewModel
import com.example.heroes.ui.heroList.HeroListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RetrofitHeroProvider.create() }
    single { NetworkHelper(App.instance) }

    factory<IHeroRepository> {
        if(BuildConfig.Mock)
            HeroRepositoryMockImpl()
        else
        HeroRepositoryImpl(get())
    } //HeroRepositoryMockImpl()
    factory<IHeroService> { HeroService(get()) }

    viewModel { HeroListViewModel(get(), get()) }
    viewModel { HeroDetailsViewModel() }
}