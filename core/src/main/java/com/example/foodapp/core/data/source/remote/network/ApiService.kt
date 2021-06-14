package com.example.foodapp.core.data.source.remote.network

import com.example.foodapp.core.data.source.remote.response.ListRecipeResponse
import com.example.foodapp.core.data.source.remote.response.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("search.php?f=c")
    suspend fun getList(): ListRecipeResponse

    @GET("search.php?f=c/{idMeal}")
    suspend fun getDetail(@Path("idMeal") idMeal: String): RecipeResponse
}