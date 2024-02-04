package com.test.application.catalogue_screen.utils.sorting

import com.test.application.core.domain.product.Product

interface SortingStrategy {
    fun sort(products: List<Product>): List<Product>
}