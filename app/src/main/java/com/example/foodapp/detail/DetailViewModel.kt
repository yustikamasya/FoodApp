package com.example.foodapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.foodapp.core.domain.model.Recipe
import com.example.foodapp.core.domain.usecase.RecipeUseCase

class DetailViewModel (private val recipeUseCase: RecipeUseCase) : ViewModel(){
    fun setFavoriteRecipe(recipe: Recipe, newStatus: Boolean) = recipeUseCase.setFavoriteRecipe(recipe, newStatus)
    fun getDetailRecipe(id: String) = recipeUseCase.getDetailRecipe(id).asLiveData()
}