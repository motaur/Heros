package com.example.heros.repositories.heroRepository

import com.example.heros.api.RetrofitHeroProvider
import com.example.heros.models.HeroSearchResponse
import com.example.heros.repositories.HeroRepositoryImpl
import com.example.heros.repositories.HeroRepositoryMockImpl
import com.example.heros.repositories.IHeroRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class HeroRepositoryImplTest {

    private val retrofitHeroProvider = mockk<RetrofitHeroProvider>()
    private val heroRepositoryImpl: IHeroRepository = HeroRepositoryImpl(retrofitHeroProvider)

    @Test
    fun testSearchReturnsNoResults() {
        coEvery { retrofitHeroProvider.searchByName(any()) } returns
                HeroSearchResponse(
                    results = null,
                    response = "",
                    resultsFor = ""
                )
        val results = runBlocking { heroRepositoryImpl.searchHeroByName("Superman") }

        assertArrayEquals(emptyArray(), results.toTypedArray())
    }

    @Test
    fun testSearchReturnsEmptyResults() {
        coEvery { retrofitHeroProvider.searchByName(any()) } returns
                HeroSearchResponse(
                    results = emptyList(),
                    response = "",
                    resultsFor = ""
                )
        val results = runBlocking { heroRepositoryImpl.searchHeroByName("Superman") }

        assertArrayEquals(emptyArray(), results.toTypedArray())
    }

    @Test
    fun testSearchReturnsResults() {

        val mockRepo = HeroRepositoryMockImpl()

        val expected = runBlocking { mockRepo.searchHeroByName("Superman") }

        coEvery { retrofitHeroProvider.searchByName(any()) } returns
                HeroSearchResponse(
                    results = expected,
                    response = "",
                    resultsFor = ""
                )
        val results = runBlocking { heroRepositoryImpl.searchHeroByName("Superman") }

        assertArrayEquals(expected.toTypedArray(), results.toTypedArray())
    }


}