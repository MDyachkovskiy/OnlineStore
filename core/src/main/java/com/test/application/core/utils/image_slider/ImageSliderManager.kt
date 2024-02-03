package com.test.application.core.utils.image_slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.test.application.core.R

class ImageSliderManager(
    private val context: Context,
    private val viewPager: ViewPager2,
    private val paginationContainer: LinearLayout
) {
    private val paginationViews = mutableListOf<View>()

    fun setupSlider(imagesResIds: List<Int>) {
        viewPager.adapter = ImageSliderAdapter(context, imagesResIds)

        setupPagination(imagesResIds.size)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updatePagination(position)
            }
        })
    }

    private fun setupPagination(size : Int) {
        paginationContainer.removeAllViews()
        paginationViews.clear()

        for (i in 0 until size) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.pagination_dot, paginationContainer, false)
            paginationContainer.addView(view)
            paginationViews.add(view)
        }
        updatePagination(0)
    }

    private fun updatePagination(selectedIndex: Int) {
        paginationViews.forEachIndexed { index, view ->
            view.findViewById<CardView>(R.id.pagination_dot).setCardBackgroundColor(
                ContextCompat.getColor(context,
                    if (index == selectedIndex) R.color.element_pink else R.color.element_light_grey
                )
            )
        }
    }
}