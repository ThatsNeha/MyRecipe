package com.kotlin.magicrecipe.ui

import com.kotlin.magicrecipe.data.model.Recipes


interface AuthListener {
    fun onStarted()
    fun onSuccess(recipes: List<Recipes>)
    fun onFailure(message: String)
}