package com.test.application.core.domain.product

data class Price(
    val discount: Int = 0,
    val price: String = "",
    val priceWithDiscount: String = "",
    val unit: String = ""
)
