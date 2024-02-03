package com.test.application.product_card.utils

import android.content.Context
import android.text.TextUtils
import android.widget.TextView

class ToggleVisibilityHelper(
    private val context: Context,
    private val toggleButton: TextView,
    private val contentView: TextView
) {
    private var isExpanded = true

    init {
        setupToggleButton()
    }
    private fun setupToggleButton() {
        updateButtonText()
        toggleButton.setOnClickListener {
            isExpanded = !isExpanded
            updateView()
            updateButtonText()
        }
    }

    private fun updateView() {
        if (isExpanded) {
            contentView.maxLines = Integer.MAX_VALUE
            contentView.ellipsize = null
        } else {
            contentView.maxLines = 2
            contentView.ellipsize = TextUtils.TruncateAt.END
        }
    }

    private fun updateButtonText() {
        toggleButton.text = if (isExpanded)
            context.getString(com.test.application.core.R.string.hide_text)
        else
            context.getString(com.test.application.core.R.string.show_more_text)
    }
}