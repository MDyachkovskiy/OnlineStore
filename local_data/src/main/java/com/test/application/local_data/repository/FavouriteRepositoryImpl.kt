package com.test.application.local_data.repository

import com.test.application.core.domain.product.Product
import com.test.application.core.repository.FavouritesRepository
import com.test.application.local_data.favourite_item.ProductDao
import com.test.application.local_data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDao: ProductDao
) : FavouritesRepository {
    override suspend fun saveFavoriteItem(product: Product) {
        favouriteDao.insertProduct(product.toEntity())
        product.feedback?.let { favouriteDao.insertFeedback(it.toEntity(product.id))}

        product.info.map { info ->
            info.toEntity(product.id).also { infoEntity ->
                favouriteDao.insertInfos(infoEntity)
            }
        }
        favouriteDao.insertPrice(product.price.toEntity(product.id))
    }

    override suspend fun deleteFavoriteItem(id: String) {
        favouriteDao.deleteProductById(id)
    }

    override suspend fun checkFavoriteItems(ids: List<String>): Flow<Map<String, Boolean>> {
        return favouriteDao.getFavoriteProductIds(ids).map { favoriteIds ->
            ids.associateWith { favoriteIds.contains(it) }
        }
    }
}