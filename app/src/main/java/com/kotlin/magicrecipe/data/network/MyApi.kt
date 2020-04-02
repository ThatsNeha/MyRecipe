package com.kotlin.magicrecipe.data.network

import com.kotlin.magicrecipe.data.model.RecipeList
import com.kotlin.magicrecipe.util.AppConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface MyApi {

    @GET("api")
    suspend fun getRecipes(
    ): Response<RecipeList>


    @GET("api")
    suspend fun getQueriedRecipe(
        @Query("i") i: StringBuilder
    ): Response<RecipeList>

    companion object {
        operator fun invoke(): MyApi {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(AppConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(AppConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()


            return Retrofit.Builder()
                .baseUrl(AppConstant.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(MyApi::class.java)
        }
    }

}

