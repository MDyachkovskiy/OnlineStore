package com.test.application.product_card.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.test.application.core.domain.product.Info
import com.test.application.product_card.R

class FeaturesManager(
    private val context: Context,
    private val container: LinearLayout
) {
    fun addFeatures(features: List<Info>) {
        container.removeAllViews()
        features.forEach { feature ->
            val featureView = LayoutInflater.from(context).inflate(R.layout.item_feature, container, false)
            featureView.findViewById<TextView>(R.id.feature_title).text = feature.title
            featureView.findViewById<TextView>(R.id.feature_value).text = feature.value
            container.addView(featureView)
        }
    }
}