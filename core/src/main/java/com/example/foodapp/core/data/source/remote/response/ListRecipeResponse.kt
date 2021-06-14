package com.example.foodapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListRecipeResponse(
    @SerializedName("meals")
    val meals: List<RecipeResponse>? = null
)
