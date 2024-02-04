package com.test.application.product_card.utils

import android.text.TextUtils
import android.transition.TransitionManager
import android.view.ViewGroup
import android.widget.TextView

class ToggleTextVisibilityHelper(
    private val toggleButton: TextView,
    private val contentView: TextView,
    private val container: ViewGroup
) {
    private var isExpanded = false

    init {
        contentView.post {
            if (contentView.lineCount > 2) {
                toggleButton.visibility = TextView.VISIBLE
                toggleButton.text =
                    contentView.context.getString(com.test.application.core.R.string.show_more_text)
                contentView.maxLines = 2
                contentView.ellipsize = TextUtils.TruncateAt.END
            } else {
                toggleButton.visibility = TextView.GONE
            }
        }

        setupToggleButton()
    }

    private fun setupToggleButton() {
        toggleButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(container)
            isExpanded = !isExpanded
            updateView()
        }
    }

    private fun updateView() {
        TransitionManager.beginDelayedTransition(container)

        if (isExpanded) {
            contentView.maxLines = Integer.MAX_VALUE
            contentView.ellipsize = null
            toggleButton.text =
                contentView.context.getString(com.test.application.core.R.string.hide_text)
        } else {
            contentView.maxLines = 2
            contentView.ellipsize = TextUtils.TruncateAt.END
            toggleButton.text =
                contentView.context.getString(com.test.application.core.R.string.show_more_text)
        }
    }
}