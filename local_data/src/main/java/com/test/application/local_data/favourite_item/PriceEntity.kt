package com.test.application.local_data.favourite_item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prices")
data class PriceEntity(
    @PrimaryKey val productId: String,
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
)
