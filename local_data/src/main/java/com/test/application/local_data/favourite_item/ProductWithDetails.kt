package com.test.application.local_data.favourite_item

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithDetails(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    val feedback: FeedbackEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    val infos: List<InfoEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    val price: PriceEntity
)
