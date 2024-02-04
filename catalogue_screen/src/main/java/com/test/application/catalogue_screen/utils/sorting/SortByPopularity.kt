package com.test.application.catalogue_screen.utils.sorting

import com.test.application.core.domain.product.Product

class SortByPopularity : SortingStrategy {
    override fun sort(products: List<Product>): List<Product> {
        return products.sortedByDescending { it.feedback.rating }
    }
}