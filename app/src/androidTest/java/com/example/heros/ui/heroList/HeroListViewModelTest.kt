package com.example.heros.ui.heroList

import com.example.heros.helpers.NetworkHelper
import com.example.heros.models.HeroUiModel
import com.example.heros.services.IHeroService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HeroListViewModelTest {

    private val heroService = mockk<IHeroService>()
    private val networkHelper = mockk<NetworkHelper>()

    private val vm = HeroListViewModel (heroService, networkHelper)

    private val hero0 = mockk<HeroUiModel>()
    private val hero1 = mockk<HeroUiModel>()
    private val hero2 = mockk<HeroUiModel>()

    private val listOfHeroes = listOf(
        hero0, hero1, hero2
    )

    private val listOfSuggestions = listOf("1", "2", "3")

    @Before
    fun init() {
        every { hero0.id } returns listOfSuggestions[0]
        every { hero1.id } returns listOfSuggestions[1]
        every { hero2.id } returns listOfSuggestions[2]

        coEvery { heroService.getSuggestions(any()) } returns listOfHeroes
        coEvery { heroService.searchHeroByName(any()) } returns listOfHeroes
    }

    @Test
    fun setSearchTextBlank() {
        vm.searchText = ""
        runBlocking { delay(1000) }
        assertTrue(vm.adapter.itemCount == 0)
    }

    @Test
    fun setSearchText() {
        vm.searchText = "Superman"
        runBlocking { delay(1000) }
        assertEquals(listOfHeroes.size, vm.adapter.itemCount)
    }

    @Test
    fun fetchSuggestions() {
        runBlocking { vm.fetchSuggestions() }
        vm.suggestions.forEachIndexed{ i,e ->
            assertEquals(listOfSuggestions[i], e.id)
        }
    }

    @Test
    fun fetchSuggestionsErrorTest() {
        coEvery { heroService.getSuggestions(any()) } throws Exception()
        runBlocking { vm.fetchSuggestions() }
        assertTrue(vm.suggestions.isEmpty())
    }
}