package com.example.heros.ui.heroList

import android.graphics.Color
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
import kotlin.random.Random

class HeroListViewModel(private val heroService: IHeroService) : ViewModel(),
    LifecycleObserver {

    val progressVisibility = MutableLiveData(INVISIBLE)
    val nothingFoundVisibility = MutableLiveData(GONE)
    val errorVisibility = MutableLiveData(GONE)

    var suggestions: List<HeroUiModel> = emptyList()

    private var heroesList: List<HeroUiModel> = emptyList()
    val adapter: HeroesAdapter = HeroesAdapter()

    var searchText: String = ""
        set(value) {
            field = value
            viewModelScope.launch {
                if(value.isNotBlank())
                    searchHeroByName(value)
            }
        }

    suspend fun fetchSuggestions() {
        try {
            suggestions = heroService.getSuggestions()
        }
        catch (e: Exception){
            Log.e("getSuggestions", e.toString())
        }
    }

    private suspend fun searchHeroByName(query: String) {

        progressVisibility.value = VISIBLE
        try {
            adapter.setList(emptyList())
            val result = heroService.searchHeroByName(query)
            Log.v("search heroes", result.toString())
            heroesList = result
            progressVisibility.value = INVISIBLE

            if(result.isEmpty())
                nothingFoundVisibility.value = VISIBLE
            else {
                nothingFoundVisibility.value = GONE
                adapter.setList(result)
            }
        }
        catch (e: Exception){
            errorVisibility.value = VISIBLE
            progressVisibility.value = INVISIBLE
            nothingFoundVisibility.value = GONE

            Log.e("search heroes", e.toString())
        }
    }

    fun randomColor()  = Color.argb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

}