package com.test.application.core.repository

import com.test.application.core.domain.product.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatalogueInteractorImpl @Inject constructor(
    private val remoteData: RemoteDataRepository,
    private val favouritesRepository: FavouritesRepository
) : CatalogueInteractor {
    override suspend fun getProducts(): Flow<List<Product>> {
        return flow {
            val products = remoteData.getProducts()
            emit(products)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveFavoriteItem(product: Product) {
        favouritesRepository.saveFavoriteItem(product)
    }

    override suspend fun deleteFavoriteItem(id: String) {
        favouritesRepository.deleteFavoriteItem(id)
    }

    override suspend fun checkFavoriteItems(ids: List<String>): Flow<Map<String, Boolean>> {
        return favouritesRepository.checkFavoriteItems(ids)
    }
}