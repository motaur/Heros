package com.example.heroes.services

import com.example.heroes.repositories.HeroRepositoryMockImpl
import com.example.heroes.repositories.IHeroRepository
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HeroServiceTest {

    private val heroRepository: IHeroRepository = spyk<HeroRepositoryMockImpl>()
    private val heroService: IHeroService = HeroService(heroRepository)

    @Test
    fun searchHeroByNameTest() {
        val mockList = (heroRepository as HeroRepositoryMockImpl).mockListofHeroes
        val results = runBlocking { heroService.searchHeroByName("") }

        mockList.forEachIndexed { i, e ->
            assertEquals(mockList[i].id, results[i].id)
        }
    }

    @Test
    fun searchHeroByNameReturnsEmptyTest() {
        coEvery { heroRepository.searchHeroByName(any()) } returns emptyList() // nothing found
        val results = runBlocking { heroService.searchHeroByName("") }
        assertTrue(results.isEmpty())
    }

    @Test
    fun getSuggestionsTest() {
        val suggestionsIds = listOf("1", "2", "3")
        val suggestions = runBlocking { heroService.getSuggestions(suggestionsIds) } // mock suggestion regardless of arguments
        assertEquals(suggestionsIds.size, suggestions.size)
        suggestions.forEachIndexed { i, element ->
            assertEquals(suggestionsIds[i], element.id)
        }
    }

    @Test
    fun getSuggestionsReturnsIdNullTest() {
        val suggestionsIds = listOf("1", "2", "3")
        coEvery { heroRepository.getById(any()) } returns null // nothing found with this id
        val suggestions = runBlocking { heroService.getSuggestions(suggestionsIds) }
        assertEquals(0, suggestions.size)
    }
}