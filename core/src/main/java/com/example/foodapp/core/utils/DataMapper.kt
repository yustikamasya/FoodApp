package com.example.foodapp.core.utils

import com.example.foodapp.core.data.source.local.entity.RecipeEntity
import com.example.foodapp.core.data.source.remote.response.RecipeResponse
import com.example.foodapp.core.domain.model.Recipe

object DataMapper {
    fun mapResponsesToEntities(input: List<RecipeResponse>): List<RecipeEntity> {
        val recipeList = ArrayList<RecipeEntity>()
        input.map{
            val meals = RecipeEntity(
                idMeal = it.idMeal,
                meal = it.strMeal,
                category = it.strCategory,
                instructions = it.strInstructions,
                image = it.strMealThumb,
                ingredient1 = it.strIngredient1,
                ingredient2 = it.strIngredient2,
                ingredient3 = it.strIngredient3,
                ingredient4 = it.strIngredient4,
                ingredient5 = it.strIngredient5,
                measure1 = it.strMeasure1,
                measure2 = it.strMeasure2,
                measure3 = it.strMeasure3,
                measure4 = it.strMeasure4,
                measure5 = it.strMeasure5,
                isFavorite = false
            )
            recipeList.add(meals)
        }
        return recipeList
    }

    fun mapEntitiesToDomain(input: List<RecipeEntity>): List<Recipe> =
        input.map {
            Recipe(
                idMeal = it.idMeal,
                meal = it.meal,
                category = it.category,
                instructions = it.instructions,
                image = it.image,
                ingredient1 = it.ingredient1,
                ingredient2 = it.ingredient2,
                ingredient3 = it.ingredient3,
                ingredient4 = it.ingredient4,
                ingredient5 = it.ingredient5,
                measure1 = it.measure1,
                measure2 = it.measure2,
                measure3 = it.measure3,
                measure4 = it.measure4,
                measure5 = it.measure5,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Recipe) = RecipeEntity(
        idMeal = input.idMeal,
        meal = input.meal,
        category = input.category,
        instructions = input.instructions,
        image = input.image,
        ingredient1 = input.ingredient1,
        ingredient2 = input.ingredient2,
        ingredient3 = input.ingredient3,
        ingredient4 = input.ingredient4,
        ingredient5 = input.ingredient5,
        measure1 = input.measure1,
        measure2 = input.measure2,
        measure3 = input.measure3,
        measure4 = input.measure4,
        measure5 = input.measure5,
        isFavorite = input.isFavorite
    )

    fun mapEntityToDomain(input: RecipeEntity) = Recipe(
        idMeal = input.idMeal,
        meal = input.meal,
        category = input.category,
        instructions = input.instructions,
        image = input.image,
        ingredient1 = input.ingredient1,
        ingredient2 = input.ingredient2,
        ingredient3 = input.ingredient3,
        ingredient4 = input.ingredient4,
        ingredient5 = input.ingredient5,
        measure1 = input.measure1,
        measure2 = input.measure2,
        measure3 = input.measure3,
        measure4 = input.measure4,
        measure5 = input.measure5,
        isFavorite = input.isFavorite
    )

    fun mapResponsesDetailToEntities(input: RecipeResponse): RecipeEntity{
        return RecipeEntity(
            idMeal = input.idMeal,
            meal = input.strMeal,
            category = input.strCategory,
            instructions = input.strInstructions,
            image = input.strMealThumb,
            ingredient1 = input.strIngredient1,
            ingredient2 = input.strIngredient2,
            ingredient3 = input.strIngredient3,
            ingredient4 = input.strIngredient4,
            ingredient5 = input.strIngredient5,
            measure1 = input.strMeasure1,
            measure2 = input.strMeasure2,
            measure3 = input.strMeasure3,
            measure4 = input.strMeasure4,
            measure5 = input.strMeasure5,
        )
    }


}