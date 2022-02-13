package com.example.heroes.ui.heroList

import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heroes.Constants
import com.example.heroes.R
import com.example.heroes.helpers.NetworkHelper
import com.example.heroes.models.HeroUiModel
import com.example.heroes.models.RequestState
import com.example.heroes.services.IHeroService
import kotlinx.coroutines.launch
import kotlin.random.Random

class HeroListViewModel(private val heroService: IHeroService, val networkHelper: NetworkHelper) : ViewModel() {

    private val colors = listOf(
        R.color.yellow,
        R.color.red,
        R.color.orange,
        R.color.purple_200,
        R.color.violet,
        R.color.green,
        R.color.teal_200
    )

    private val suggestionsIds = listOf(Constants.SUGGESTIONS_ID_1 , Constants.SUGGESTIONS_ID_2, Constants.SUGGESTIONS_ID_3)

    fun getSuggestionColor() = colors[Random.nextInt(colors.size)]

    val progressVisibility = MutableLiveData(GONE)
    val nothingFoundVisibility = MutableLiveData(GONE)
    val errorVisibility = MutableLiveData(GONE)
    val hintVisibility = MutableLiveData(VISIBLE)

    var suggestions: List<HeroUiModel> = emptyList()

    private var heroesList: List<HeroUiModel> = emptyList()
    val adapter: HeroAdapter = HeroAdapter()

    var searchText: String = ""
        set(value) {
            field = value
            viewModelScope.launch {
                if(value.isNotBlank())
                    searchHeroByName(value)
            }
        }

    suspend fun fetchSuggestions() {
        suggestions = emptyList()
        try {
            suggestions = heroService.getSuggestions(suggestionsIds)
        }
        catch (e: Exception){
            Log.e("getSuggestions", e.toString())
        }
    }

    private suspend fun searchHeroByName(query: String) {
        setUiFromSearchRequestState(RequestState.Loading)
        try {
            val result = heroService.searchHeroByName(query)
            Log.v("search heroes", result.toString())

            if(result.isEmpty())
                setUiFromSearchRequestState(RequestState.Empty)
            else {
                setUiFromSearchRequestState(RequestState.Success)
                adapter.setList(result)
                heroesList = result
            }
        }
        catch (e: Exception){
            Log.e("search heroes", e.toString())
            setUiFromSearchRequestState(RequestState.Error)
        }
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
}