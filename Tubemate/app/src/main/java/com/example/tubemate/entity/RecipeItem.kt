package com.example.tubemate.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipe_table")

data class RecipeItem(
    val title: String,
    val thumbnailUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
): Parcelable
