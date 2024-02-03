package com.test.application.product_card.utils

import android.content.Context
import com.test.application.product_card.R

fun getStockQuantityString(context: Context, quantity: Int): String {
    val resourceId = when {
        quantity % 10 == 1 && quantity % 100 != 11 -> context.getString(R.string.product_one_stock)
        quantity % 10 in 2..4 && quantity % 100 !in 12..14 -> context.getString(R.string.product_few_stock)
        else -> context.getString(R.string.product_many_stock)
    }
    return context.getString(R.string.available_products, quantity, resourceId)
}