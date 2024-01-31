package com.example.tubemate.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tubemate.R
import com.example.tubemate.adapter.ProductAdapter
import com.example.tubemate.adapter.RecipeAdapter
import com.example.tubemate.databinding.FragmentHomeBinding
import com.example.tubemate.entity.ProductItem
import com.example.tubemate.entity.RecipeItem
import com.example.tubemate.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home){

    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val recipeAdapter = RecipeAdapter()
        val productAdapter = ProductAdapter().apply {
           onItemClick = {
               findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFullScreenFragment())
           }
        }

        binding.apply {

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getRecipes()
                viewModel.getProducts()
            }


            viewModel.recipe.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> {
                        swipeRefreshLayout.isRefreshing = true
                    }
                    is Resource.Failure -> {
                        swipeRefreshLayout.isRefreshing = false
                    }
                    is Resource.Success -> {
                        val list = it.value.recipes.map { recipe ->
                            RecipeItem(recipe.name, recipe.image, recipe.id)
                        }.toList().take(10)
                        viewModel.insertRecipe(list)
                        swipeRefreshLayout.isRefreshing = false
                        viewModel.cacheRecipe()

                    }
                }
            }

            viewModel.products.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> {
                        swipeRefreshLayout.isRefreshing = true

                    }
                    is Resource.Failure -> {
                        swipeRefreshLayout.isRefreshing = false
                    }
                    is Resource.Success -> {
                        val list = it.value.products.map { product ->
                            ProductItem(product.title,product.description, product.thumbnail,product.id)
                        }.toList().take(10)
                        viewModel.insertProduct(list)
                        swipeRefreshLayout.isRefreshing = false
                        viewModel.cacheProducts()

                    }
                }
            }


            rvShorts.apply {
                val llm = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
                layoutManager = llm
                adapter = recipeAdapter
            }

            recyclerView.apply {
                val llm = LinearLayoutManager(context)
                layoutManager = llm
                adapter = productAdapter
            }

            viewModel.cacheRecipe()
            viewModel.cacheProducts()

            viewModel.cacheRecipe.observe(viewLifecycleOwner){
                if (it.isEmpty()) viewModel.getRecipes()
                recipeAdapter.submitList(it)
            }

            viewModel.cacheProducts.observe(viewLifecycleOwner){
                if (it.isEmpty()) viewModel.getProducts()
                productAdapter.submitList(it)
            }


        }


    }



}
