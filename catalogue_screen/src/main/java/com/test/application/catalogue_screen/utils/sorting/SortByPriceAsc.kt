package com.test.application.catalogue_screen.utils.sorting

import com.test.application.core.domain.product.Product

class SortByPriceAsc : SortingStrategy {
    override fun sort(products: List<Product>): List<Product> {
        return products.sortedBy { it.price.priceWithDiscount.toDoubleOrNull() ?: 0.0 }
    }
}