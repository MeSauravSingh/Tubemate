package com.example.tubemate.data

data class RecipeResponse(
    val recipes: List<Recipe>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)

data class Recipe(
    val id: Long,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTimeMinutes: Long,
    val cookTimeMinutes: Long,
    val servings: Long,
    val difficulty: String,
    val cuisine: String,
    val caloriesPerServing: Long,
    val tags: List<String>,
    val userId: Long,
    val image: String,
    val rating: Double,
    val reviewCount: Long,
    val mealType: List<String>,
)