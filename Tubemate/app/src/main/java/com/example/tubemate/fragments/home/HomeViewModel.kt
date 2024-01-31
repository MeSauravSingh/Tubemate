package com.example.tubemate.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tubemate.entity.ProductItem
import com.example.tubemate.data.ProductResponse
import com.example.tubemate.data.RecipeResponse
import com.example.tubemate.entity.RecipeItem
import com.example.tubemate.network.Resource
import com.example.tubemate.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {

    /** Fetching data from API ------> START  **/

    private val _recipe: MutableLiveData<Resource<RecipeResponse>> = MutableLiveData()
    val recipe: LiveData<Resource<RecipeResponse>>
        get() = _recipe

    fun getRecipes() = viewModelScope.launch {
        _recipe.value = Resource.Loading
        _recipe.value = repository.getRecipes()
    }

    private val _products: MutableLiveData<Resource<ProductResponse>> = MutableLiveData()
    val products: LiveData<Resource<ProductResponse>>
        get() = _products

    fun getProducts() = viewModelScope.launch {
        _products.value = Resource.Loading
        _products.value = repository.getProducts()
    }

    /** Fetching data from API ------> END  **/



    /** Fetching data from LOCAL CACHE(ROOM-DB)  **/

    fun insertRecipe(
        recipe: List<RecipeItem>
    ) = viewModelScope.launch(Dispatchers.IO){
        repository.insertRecipe(recipe)
    }

    fun deleteAllRecipe() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAllRecipe()
    }


    private val _cacheRecipe: MutableLiveData<List<RecipeItem>> = MutableLiveData()
    val cacheRecipe: LiveData<List<RecipeItem>>
        get() = _cacheRecipe

    fun cacheRecipe() = viewModelScope.launch {
        _cacheRecipe.value = repository.cacheRecipes()
    }





    fun insertProduct(
        product: List<ProductItem>
    ) = viewModelScope.launch(Dispatchers.IO){
        repository.insertProduct(product)
    }
    fun deleteAllProduct() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAllProducts()
    }


    private val _cacheProducts: MutableLiveData<List<ProductItem>> = MutableLiveData()
    val cacheProducts: LiveData<List<ProductItem>>
        get() = _cacheProducts

    fun cacheProducts() = viewModelScope.launch {
        _cacheProducts.value = repository.cacheProducts()
    }

}
