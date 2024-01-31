package com.example.tubemate.repository

import com.example.tubemate.entity.ProductItem
import com.example.tubemate.entity.RecipeItem
import com.example.tubemate.network.UserApi
import com.example.tubemate.room.ProductDao
import com.example.tubemate.room.RecipeDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val api: UserApi,
    private val recipeDao: RecipeDao,
    private val productDao: ProductDao
) : BaseRepository() {

    suspend fun getRecipes() = safeApiCall {
        api.getRecipes()
    }
    suspend fun getProducts() = safeApiCall {
        api.getProducts()
    }



    suspend fun insertRecipe(recipe: List<RecipeItem>){
        recipeDao.insertRecipe(recipe)
    }
    suspend fun deleteAllRecipe(){
        recipeDao.deleteAllRecipe()
    }
    suspend fun cacheRecipes() = recipeDao.cacheRecipe()



    suspend fun insertProduct(product: List<ProductItem>){
        productDao.insertProduct(product)
    }
    suspend fun deleteAllProducts(){
        productDao.deleteAllProducts()
    }

    suspend fun cacheProducts() = productDao.cacheProducts()


}