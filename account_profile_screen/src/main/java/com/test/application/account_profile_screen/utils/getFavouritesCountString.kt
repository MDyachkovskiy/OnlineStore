package com.test.application.account_profile_screen.utils

import android.content.Context
import com.test.application.account_profile_screen.R

fun getFavouritesCountString(context: Context, quantity: Int) : String {
    val quantityString = when {
        quantity % 10 == 1 && quantity % 100 != 11 -> context.getString(R.string.favourites_one_item)
        quantity % 10 in 2..4 && quantity % 100 !in 12..14 -> context.getString(R.string.favourites_few_items)
        else -> context.getString(R.string.favourites_many_items)
    }
    return context.getString(R.string.formated_favourite_item, quantity, quantityString)
}