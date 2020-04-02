package com.kotlin.magicrecipe.data.model

data class RecipeList(
    val title: String,
    val version: String,
    val href: String,
    val results: List<Recipes>
)