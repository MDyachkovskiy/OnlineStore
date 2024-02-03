package com.test.application.product_card.utils

import android.view.View
import android.widget.Button
import android.widget.TextView

class ToggleViewVisibilityHelper(
    private val toggleButton: TextView,
    private val brandButton: Button,
    private val descriptionView: TextView
) {
    private var isContentVisible = true

    init {
        setupToggleButton()
        updateViewsVisibility()
    }
    private fun setupToggleButton() {
        updateButtonText()
        toggleButton.setOnClickListener {
            isContentVisible = !isContentVisible
            updateViewsVisibility()
            updateButtonText()
        }
    }

    private fun updateViewsVisibility()  {
        val visibility = if(isContentVisible) View.VISIBLE else View.GONE
        descriptionView.visibility = visibility
        brandButton.visibility = visibility
    }

    private fun updateButtonText() {
        toggleButton.text = if (isContentVisible)
            toggleButton.context.getString(com.test.application.core.R.string.hide_text)
        else
            toggleButton.context.getString(com.test.application.core.R.string.show_more_text)
    }
}