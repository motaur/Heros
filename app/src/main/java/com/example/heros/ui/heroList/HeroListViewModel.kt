package com.example.heros.ui.heroList

import androidx.lifecycle.ViewModel
import com.example.heros.repositories.heroRepository.HeroRepository

class HeroListViewModel(heroRepository: HeroRepository) : ViewModel() {

    private val adapter: HeroesAdapter = HeroesAdapter({})

}