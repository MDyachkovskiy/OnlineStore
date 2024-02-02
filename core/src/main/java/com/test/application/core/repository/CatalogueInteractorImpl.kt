package com.test.application.core.repository

import com.test.application.core.domain.product.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatalogueInteractorImpl @Inject constructor(
    private val remoteData: RemoteDataRepository,
    private val localData: LocalDataRepository
) : CatalogueInteractor {
    override suspend fun getProducts(): Flow<List<Product>> {
        return flow {
            val products = remoteData.getProducts()
            emit(products)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveFavoriteItem(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteItem(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun checkFavoriteItem(id: String): Boolean {
        TODO("Not yet implemented")
    }
}