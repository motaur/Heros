package com.example.heros.ui.heroList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heros.models.HeroUiModel
import com.example.heros.repositories.heroRepository.HeroRepository
import kotlinx.coroutines.launch


class HeroListViewModel(private val heroRepository: HeroRepository) : ViewModel() {
    val adapter: HeroesAdapter = HeroesAdapter()
    val progressVisible = MutableLiveData(true)
    var heroesList: List<HeroUiModel> = emptyList()

    var searchText: String = ""
        set(value) {
            field = value
            adapter.setList(heroesList, value)
        }

    fun init() {
        viewModelScope.launch {
            val result = heroRepository.getHeroList()

            if(result.isNotEmpty()) {
                progressVisible.value = false
                heroesList = result
                adapter.setList(result)
            }
        }

    }

}