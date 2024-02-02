package com.test.application.remote_data.mapper

import com.test.application.core.domain.product.Feedback
import com.test.application.core.domain.product.Info
import com.test.application.core.domain.product.Price
import com.test.application.core.domain.product.Product
import com.test.application.remote_data.dto.FeedbackDTO
import com.test.application.remote_data.dto.InfoDTO
import com.test.application.remote_data.dto.ItemDTO
import com.test.application.remote_data.dto.PriceDTO

fun ItemDTO.toDomain(): Product {
    return Product(
        available = available,
        description = description,
        feedback = feedback.toDomain(),
        id = id,
        info = info.map { it.toDomain() },
        ingredients = ingredients,
        price = price.toDomain(),
        subtitle = subtitle,
        tags = tags,
        title = title)
}

fun FeedbackDTO.toDomain(): Feedback {
    return Feedback(
        count = count,
        rating = rating
    )
}

fun InfoDTO.toDomain(): Info {
    return Info(
        title = title,
        value = value
    )
}

fun PriceDTO.toDomain(): Price {
    return Price (
        discount = discount,
        price = price,
        priceWithDiscount = priceWithDiscount,
        unit = unit
    )
}