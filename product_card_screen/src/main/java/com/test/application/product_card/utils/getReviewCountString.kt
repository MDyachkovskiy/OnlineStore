package com.test.application.product_card.utils

import android.content.Context
import com.test.application.product_card.R

fun getReviewCountString(context: Context, quantity: Int) : String {
    val quantityString = when {
        quantity % 10 == 1 && quantity % 100 != 11 -> context.getString(R.string.one_review)
        quantity % 10 in 2..4 && quantity % 100 !in 12..14 -> context.getString(R.string.few_reviews)
        else -> context.getString(R.string.many_reviews)
    }
    return context.getString(R.string.reviews_count, quantity, quantityString)
}
