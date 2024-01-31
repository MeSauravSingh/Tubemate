package com.example.tubemate.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product_table")

data class ProductItem(
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
):Parcelable
