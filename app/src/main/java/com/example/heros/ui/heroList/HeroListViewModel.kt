package com.example.heros.ui.heroList

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heros.models.HeroUiModel
import com.example.heros.repositories.heroRepository.HeroRepository
import kotlinx.coroutines.launch


class HeroListViewModel(private val heroRepository: HeroRepository) : ViewModel() {

    val progressVisible = MutableLiveData(true)
    var heroesList: Array<HeroUiModel> = emptyArray()
    val adapter: HeroesAdapter = HeroesAdapter(heroesList)


    var searchText: String = ""
        set(value) {
            field = value
            adapter.setList(heroesList, value)
        }

    suspend fun init() {

            val result = heroRepository.getHeroList()
            Log.v("heroes list result", result.toString())
            if(result.isNotEmpty()) {
                progressVisible.value = false
                heroesList = result
                adapter.setList(result)

    }

    }

}