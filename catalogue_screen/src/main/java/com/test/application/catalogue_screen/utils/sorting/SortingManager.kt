package com.test.application.catalogue_screen.utils.sorting

import com.test.application.catalogue_screen.adapter.ProductsAdapter

class SortingManager(
    private var productsAdapter: ProductsAdapter
) {
    private var currentSortingStrategy: SortingStrategy = SortByPopularity()

    fun onSortingOptionSelected(option: SortingOption) {
        currentSortingStrategy = when (option) {
            SortingOption.BY_POPULARITY -> SortByPopularity()
            SortingOption.BY_PRICE_DESC -> SortByPriceDesc()
            SortingOption.BY_PRICE_ASC -> SortByPriceAsc()
        }
        sortAndDisplayProducts()
    }

    private fun sortAndDisplayProducts() {
        val sortedProducts = currentSortingStrategy.sort(productsAdapter.getProducts())
        productsAdapter.updateContacts(sortedProducts)
    }
}