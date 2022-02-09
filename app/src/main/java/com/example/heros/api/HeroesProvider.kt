package com.example.heros.api

import com.example.heros.BuildConfig
import com.example.heros.models.HeroesSearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.annotation.Target

/**
 * Used to connect to the Unsplash API to fetch photos
 */
interface HeroesProvider {
    @GET("search/{query}")
    suspend fun searchByName(
        @Path("query") query: String,
    ): HeroesSearchResponse

    companion object {
        private const val BASE_URL = "https://superheroapi.com/api/${BuildConfig.HEROES_API_KEY}/"

        fun create(): HeroesProvider {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HeroesProvider::class.java)
        }
    }
}
