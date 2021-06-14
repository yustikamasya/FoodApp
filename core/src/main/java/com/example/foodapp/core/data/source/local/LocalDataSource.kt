@file:Suppress("RedundantSuspendModifier")

package com.example.foodapp.core.data.source.local

import com.example.foodapp.core.data.source.local.entity.RecipeEntity
import com.example.foodapp.core.data.source.local.room.RecipeDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource (private val recipeDao: RecipeDao){
    fun getAllRecipe(): Flow<List<RecipeEntity>> = recipeDao.getAllRecipe()

    fun getFavoriteRecipe(): Flow<List<RecipeEntity>> = recipeDao.getFavoriteRecipe()

    suspend fun insertRecipe(recipeList: List<RecipeEntity>) = recipeDao.insertRecipe(recipeList)

    fun setFavoriteRecipe(recipe: RecipeEntity, newState: Boolean){
        recipe.isFavorite = newState
        recipeDao.updateFavoriteRecipe(recipe)
    }

    fun getDetailRecipe(id: String): Flow<RecipeEntity> = recipeDao.getDetailRecipe(id)
}