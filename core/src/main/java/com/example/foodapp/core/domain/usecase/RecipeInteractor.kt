package com.example.foodapp.core.domain.usecase

import com.example.foodapp.core.data.Resource
import com.example.foodapp.core.domain.model.Recipe
import com.example.foodapp.core.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeInteractor (private val recipeRepository: IRecipeRepository): RecipeUseCase {
    override fun getAllRecipe() = recipeRepository.getAllRecipe()
    override fun getFavoriteRecipe(): Flow<List<Recipe>> = recipeRepository.getFavoriteRecipe()
    override fun setFavoriteRecipe(recipe: Recipe, state: Boolean) = recipeRepository.setFavoriteRecipe(recipe, state)
    override fun getDetailRecipe(id: String): Flow<Resource<Recipe>> = recipeRepository.getDetailRecipe(id)
}