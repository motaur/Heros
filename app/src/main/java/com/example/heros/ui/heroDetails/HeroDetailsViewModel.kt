package com.example.heros.ui.heroDetails

import androidx.lifecycle.ViewModel
import com.example.heros.models.HeroUiModel
import com.example.heros.services.IHeroService

class HeroDetailsViewModel(private val heroService: IHeroService) : ViewModel() {

    lateinit var heroUiModel: HeroUiModel
}