package com.test.application.local_data.favourite_item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info")
data class InfoEntity(
    @PrimaryKey(autoGenerate = true) val infoId: Int = 0,
    val productId: String,
    val title: String,
    val value: String
)
