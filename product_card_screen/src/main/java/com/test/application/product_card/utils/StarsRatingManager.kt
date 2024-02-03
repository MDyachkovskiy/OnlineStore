package com.test.application.product_card.utils

import android.widget.ImageView
import android.widget.LinearLayout
import com.test.application.product_card.R

class StarsRatingManager(
    private val starsContainer: LinearLayout
) {
    private val stars: List<ImageView> = (0 until starsContainer.childCount)
        .mapNotNull { starsContainer.getChildAt(it) as? ImageView }

    fun setRating(rating: Double) {
        val fullStars = rating.toInt()
        val halfStar = rating % 1 >= 0.5

        stars.forEachIndexed { index, imageView ->
            imageView.setImageResource(
                when {
                    index < fullStars -> R.drawable.ic_star
                    index == fullStars && halfStar -> R.drawable.ic_half_star
                    else -> R.drawable.ic_empty_star
                }
            )
        }
    }
}