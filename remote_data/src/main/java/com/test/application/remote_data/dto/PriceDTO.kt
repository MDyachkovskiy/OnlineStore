package com.test.application.remote_data.dto

data class PriceDTO(
    val discount: Int = 0,
    val price: String = "",
    val priceWithDiscount: String = "",
    val unit: String = ""
)