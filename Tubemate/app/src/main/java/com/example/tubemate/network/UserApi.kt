package com.example.tubemate.network

import com.example.tubemate.data.ProductResponse
import com.example.tubemate.data.RecipeResponse
import retrofit2.http.GET

interface UserApi {

    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse

    @GET("products")
    suspend fun getProducts(): ProductResponse

}
