package com.test.application.core.repository

import com.test.application.core.domain.product.Product

interface RemoteDataRepository {
    suspend fun getProducts() : List<Product>
}