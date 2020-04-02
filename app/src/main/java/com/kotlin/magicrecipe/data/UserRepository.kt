package com.kotlin.magicrecipe.data

import com.kotlin.magicrecipe.data.model.RecipeList
import com.kotlin.magicrecipe.data.network.MyApi
import com.kotlin.magicrecipe.data.network.SafeApiRequest
import java.lang.StringBuilder

class UserRepository() : SafeApiRequest() {

    suspend fun getRecipes(): RecipeList{
        return apiRequest{(MyApi().getRecipes())}
    }
    suspend fun getQueriedRecipe(list: StringBuilder): RecipeList {
        return apiRequest { (MyApi().getQueriedRecipe(list)) }
    }
}


