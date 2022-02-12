package com.example.heros.ui.heroList

import android.graphics.Color
import android.util.Log
import android.view.View.*
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heros.Helper
import com.example.heros.models.HeroUiModel
import com.example.heros.models.RequestState
import com.example.heros.services.IHeroService
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.random.Random

class HeroListViewModel(private val heroService: IHeroService, val helper: Helper) : ViewModel(),
    LifecycleObserver {

    val progressVisibility = MutableLiveData(GONE)
    val nothingFoundVisibility = MutableLiveData(GONE)
    val errorVisibility = MutableLiveData(GONE)
    val hintVisibility = MutableLiveData(VISIBLE)

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
        setUiFromSearchRequestState(RequestState.Loading)
//        try {
            val result = heroService.searchHeroByName(query)
            Log.v("search heroes", result.toString())

            if(result.isEmpty())
                setUiFromSearchRequestState(RequestState.Empty)
            else {
                setUiFromSearchRequestState(RequestState.Success)
                adapter.setList(result)
                heroesList = result
            }
//        }
//        catch (e: Exception){
//            Log.e("search heroes", e.toString())
//            setUiFromSearchRequestState(RequestState.Error)
//        }
    }

    private fun setUiFromSearchRequestState(requestState: RequestState) {
        when(requestState) {
            RequestState.Empty -> {
               progressVisibility.value = GONE
               nothingFoundVisibility.value = VISIBLE
               errorVisibility.value = GONE
               hintVisibility.value = GONE
            }
            RequestState.Error -> {
                progressVisibility.value = GONE
                nothingFoundVisibility.value = GONE
                errorVisibility.value = VISIBLE
                hintVisibility.value = GONE
            }
            RequestState.Loading -> {
                adapter.setList(emptyList())

                progressVisibility.value = VISIBLE
                nothingFoundVisibility.value = GONE
                errorVisibility.value = GONE
                hintVisibility.value = GONE
            }
            RequestState.NotStarted -> {
                progressVisibility.value = GONE
                nothingFoundVisibility.value = GONE
                errorVisibility.value = GONE
                hintVisibility.value = VISIBLE
            }
            RequestState.Success -> {
                progressVisibility.value = GONE
                nothingFoundVisibility.value = GONE
                errorVisibility.value = GONE
                hintVisibility.value = GONE
            }
        }
    }

    fun randomColor()  = Color.argb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

}