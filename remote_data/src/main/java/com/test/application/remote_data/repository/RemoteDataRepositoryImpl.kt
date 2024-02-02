package com.test.application.remote_data.repository

import com.test.application.core.domain.product.Product
import com.test.application.core.repository.RemoteDataRepository
import com.test.application.remote_data.api.CatalogueService
import com.test.application.remote_data.mapper.toDomain

class RemoteDataRepositoryImpl(
    private val catalogueService: CatalogueService
) : RemoteDataRepository {
    override suspend fun getProducts(): List<Product> {
        val catalogueDTO = catalogueService.getCatalogueData()
        return catalogueDTO.items.map { product ->
            product.toDomain()
        }
    }
}