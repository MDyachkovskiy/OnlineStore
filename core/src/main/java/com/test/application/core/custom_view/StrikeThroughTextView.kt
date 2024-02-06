package com.test.application.core.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class StrikeThroughTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = currentTextColor
        strokeWidth = resources.displayMetrics.density
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val startX = 0f
        val startY = height.toFloat()
        val stopX = width.toFloat()
        val stopY = 0f

        canvas.drawLine(startX, startY, stopX, stopY, paint)
    }
}