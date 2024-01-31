package com.example.tubemate.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tubemate.entity.RecipeItem

@Dao
interface RecipeDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(recipe: List<RecipeItem>)

    @Update
    suspend fun update(recipe: RecipeItem)

    @Delete
    suspend fun delete(recipe: RecipeItem)

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAllRecipe()

    @Query("SELECT * FROM recipe_table")
    suspend fun cacheRecipe(): List<RecipeItem>

}