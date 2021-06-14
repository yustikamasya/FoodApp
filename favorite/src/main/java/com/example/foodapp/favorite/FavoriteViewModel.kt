package com.example.foodapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.foodapp.core.domain.usecase.RecipeUseCase

class FavoriteViewModel (recipeUseCase: RecipeUseCase) : ViewModel() {
    val favoriteRecipe = recipeUseCase.getFavoriteRecipe().asLiveData()
}