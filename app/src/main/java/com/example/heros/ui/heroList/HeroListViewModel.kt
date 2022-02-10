package com.example.heros.ui.heroList

import android.util.Log
import android.view.View.*
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heros.models.HeroUiModel
import com.example.heros.services.IHeroService
import kotlinx.coroutines.launch
import java.lang.Exception

class HeroListViewModel(private val heroService: IHeroService) : ViewModel(),
    LifecycleObserver {

    val progressVisibility = MutableLiveData(GONE)
    val nothingFoundVisibility = MutableLiveData(GONE)
    val errorVisibility = MutableLiveData(GONE)

    var heroesList: List<HeroUiModel> = emptyList()
    val adapter: HeroesAdapter = HeroesAdapter()

    var searchText: String = ""
        set(value) {
            field = value

            viewModelScope.launch {
                if(value.isNotBlank())
                    searchHeroByName(value)
            }
        }

    private suspend fun searchHeroByName(query: String) {
        progressVisibility.value = VISIBLE
        try {
            adapter.setList(emptyList())
            val result = heroService.searchHeroByName(query)
            Log.v("search heroes", result.toString())
            heroesList = result
            progressVisibility.value = GONE

            if(result.isEmpty())
                nothingFoundVisibility.value = VISIBLE
            else {
                nothingFoundVisibility.value = GONE
                adapter.setList(result)
            }
        }
        catch (e: Exception){
            errorVisibility.value = VISIBLE
            progressVisibility.value = GONE
            nothingFoundVisibility.value = GONE

            Log.e("search heroes", e.toString())
        }
    }

}