package com.test.application.product_card.utils

import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ToggleViewVisibilityHelper(
    private val toggleButton: TextView,
    private val brandButton: Button,
    private val descriptionView: TextView,
    private val containerView: ViewGroup
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
            animateViewsVisibility()
        }
    }

    private fun animateViewsVisibility() {
        val transition = android.transition.AutoTransition().apply {
            addListener(object : Transition.TransitionListener {
                override fun onTransitionStart(transition: Transition) {}

                override fun onTransitionEnd(transition: Transition) { updateButtonText() }

                override fun onTransitionCancel(transition: Transition) { updateButtonText() }

                override fun onTransitionPause(transition: Transition) {}

                override fun onTransitionResume(transition: Transition) {}
            })
        }
        TransitionManager.beginDelayedTransition(containerView, transition)
        updateViewsVisibility()
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