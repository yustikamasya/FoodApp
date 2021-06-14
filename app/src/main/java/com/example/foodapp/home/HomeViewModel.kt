package com.example.foodapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.foodapp.core.domain.usecase.RecipeUseCase

class HomeViewModel(recipeUseCase: RecipeUseCase): ViewModel() {
    val recipe = recipeUseCase.getAllRecipe().asLiveData()
}