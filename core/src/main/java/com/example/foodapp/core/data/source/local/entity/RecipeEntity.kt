package com.example.foodapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe" )
data class RecipeEntity(
    @PrimaryKey
    @ColumnInfo(name = "idMeal")
    var idMeal: String,

    @ColumnInfo(name = "strMeal")
    var meal: String,

    @ColumnInfo(name = "strCategory")
    var category: String,

    @ColumnInfo(name = "strInstructions")
    var instructions: String,

    @ColumnInfo(name = "strMealThumb")
    var image: String,

    @ColumnInfo(name = "strIngredient1")
    var ingredient1: String?,

    @ColumnInfo(name = "strIngredient2")
    var ingredient2: String?,

    @ColumnInfo(name = "strIngredient3")
    var ingredient3: String?,

    @ColumnInfo(name = "strIngredient4")
    var ingredient4: String?,

    @ColumnInfo(name = "strIngredient5")
    var ingredient5: String?,

    @ColumnInfo(name = "strMeasure1")
    var measure1: String?,

    @ColumnInfo(name = "strMeasure2")
    var measure2: String?,

    @ColumnInfo(name = "strMeasure3")
    var measure3: String?,

    @ColumnInfo(name = "strMeasure4")
    var measure4: String?,

    @ColumnInfo(name = "strMeasure5")
    var measure5: String?,

    @ColumnInfo(name = "isFavorite") 
    var isFavorite: Boolean = false
)
