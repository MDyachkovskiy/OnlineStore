package com.test.application.local_data.favourite_item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val available: Int,
    val description: String,
    val ingredients: String,
    val subtitle: String,
    val title: String,
    val isFavourite: Boolean,
    val imageResIds: List<Int>
)
