package com.example.tubemate.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tubemate.entity.ProductItem
import com.example.tubemate.entity.RecipeItem

@Database(entities = [RecipeItem::class,ProductItem::class], version = 3, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun productDao(): ProductDao
}
