package com.test.application.core.repository

import com.test.application.core.domain.product.Product
import kotlinx.coroutines.flow.Flow

interface CatalogueInteractor {
    suspend fun getProducts(): Flow<List<Product>>
    suspend fun saveFavoriteItem(id: String)
    suspend fun deleteFavoriteItem(id: String)
    suspend fun checkFavoriteItem(id: String) : Boolean
}