package com.example.heros

import com.example.heros.api.RetrofitHeroProvider
import com.example.heros.helpers.NetworkHelper
import com.example.heros.repositories.HeroRepositoryImpl
import com.example.heros.repositories.HeroRepositoryMockImpl
import com.example.heros.repositories.IHeroRepository
import com.example.heros.services.HeroService
import com.example.heros.services.IHeroService
import com.example.heros.ui.heroDetails.HeroDetailsViewModel
import com.example.heros.ui.heroList.HeroListViewModel
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