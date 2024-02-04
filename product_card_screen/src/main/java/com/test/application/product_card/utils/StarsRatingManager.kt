package com.test.application.product_card.utils

import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.test.application.product_card.R

class StarsRatingManager(
    private val starsContainer: LinearLayout
) {
    private val context = starsContainer.context
    private val fullStarSize = context.resources.getDimensionPixelSize(R.dimen.full_star_size)
    private val fullStarHeight = context.resources.getDimensionPixelSize(R.dimen.full_star_height)
    private val emptyStarSize = context.resources.getDimensionPixelSize(R.dimen.empty_star_size)
    private val emptyStarHeight = context.resources.getDimensionPixelSize(R.dimen.empty_star_height)

    private val stars: List<ImageView> = (0 until starsContainer.childCount)
        .mapNotNull { starsContainer.getChildAt(it) as? ImageView }

    fun setRating(rating: Double) {
        val fullStars = rating.toInt()
        val halfStar = rating % 1 >= 0.5

        stars.forEachIndexed { index, imageView ->
            when {
                index < fullStars -> {
                    imageView.setImageResource(R.drawable.ic_star)
                    updateLayoutParams(imageView, fullStarSize, fullStarHeight)
                }
                index == fullStars && halfStar -> {
                    imageView.setImageResource(R.drawable.ic_half_star)
                    updateLayoutParams(imageView, fullStarSize, fullStarHeight)
                }
                else -> {
                    imageView.setImageResource(R.drawable.ic_empty_star)
                    updateLayoutParams(imageView, emptyStarSize, emptyStarHeight)
                }
            }
        }
    }

    private fun updateLayoutParams(imageView: ImageView, width: Int, height: Int) {
        imageView.layoutParams = LinearLayout.LayoutParams(width, height).apply {
            gravity = Gravity.CENTER
        }
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
    }
}