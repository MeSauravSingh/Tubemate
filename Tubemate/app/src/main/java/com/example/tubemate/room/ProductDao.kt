package com.example.tubemate.room

import androidx.room.*
import androidx.room.Dao
import com.example.tubemate.entity.ProductItem


@Dao
interface ProductDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: List<ProductItem>)

    @Update
    suspend fun update(productItem: ProductItem)

    @Delete
    suspend fun delete(productItem: ProductItem)

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM product_table")
    suspend fun cacheProducts(): List<ProductItem>

}