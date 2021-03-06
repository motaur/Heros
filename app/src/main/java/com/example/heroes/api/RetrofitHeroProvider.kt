package com.example.heroes.api

import com.example.heroes.BuildConfig
import com.example.heroes.models.HeroApiModel
import com.example.heroes.models.HeroSearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitHeroProvider {

    @GET("search/{query}")
    suspend fun searchByName(
        @Path("query") query: String
    ): HeroSearchResponse

    @GET("{id}")
    suspend fun getById(
        @Path("id") id: String)
    : HeroApiModel?

    companion object {
        private const val BASE_URL = "https://superheroapi.com/api/${BuildConfig.HEROES_API_KEY}/"

        fun create(): RetrofitHeroProvider {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitHeroProvider::class.java)
        }
    }
}
