package com.example.foodapp.core.domain.repository

import com.example.foodapp.core.data.Resource
import com.example.foodapp.core.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {
    fun getAllRecipe(): Flow<Resource<List<Recipe>>>

    fun getFavoriteRecipe(): Flow<List<Recipe>>

    fun setFavoriteRecipe(recipe: Recipe, state: Boolean)

    fun getDetailRecipe(id: String): Flow<Resource<Recipe>>
}