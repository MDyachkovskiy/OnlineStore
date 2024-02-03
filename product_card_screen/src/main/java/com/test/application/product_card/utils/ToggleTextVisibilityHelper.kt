package com.test.application.product_card.utils

import android.text.TextUtils
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView

class ToggleTextVisibilityHelper(
    private val toggleButton: TextView,
    private val contentView: TextView
) {
    private var isExpanded = false

    init {
        setupToggleButton()
        checkTextFits()
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
            toggleButton.context.getString(com.test.application.core.R.string.hide_text)
        else
            toggleButton.context.getString(com.test.application.core.R.string.show_more_text)
    }
    private fun checkTextFits() {
        contentView.viewTreeObserver
            .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (contentView.lineCount <= 2) {
                    toggleButton.visibility = View.GONE
                } else {
                    toggleButton.visibility = View.VISIBLE
                }
                contentView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

}