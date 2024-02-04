package com.test.application.local_data.favourite_item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FeedbackEntity(
    @PrimaryKey(autoGenerate = true) val feedbackId: Int = 0,
    val productId: String,
    val count: Int,
    val rating: Double
)
