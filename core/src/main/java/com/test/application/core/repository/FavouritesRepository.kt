package com.test.application.core.repository

import com.test.application.core.domain.product.Product
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun saveFavoriteItem(product: Product)
    suspend fun deleteFavoriteItem(id: String)
    suspend fun checkFavoriteItems(ids: List<String>): Flow<Map<String, Boolean>>
}