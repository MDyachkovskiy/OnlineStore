package com.test.application.local_data.mapper

import com.test.application.core.domain.auth.UserLogin
import com.test.application.core.domain.product.Feedback
import com.test.application.core.domain.product.Info
import com.test.application.core.domain.product.Price
import com.test.application.core.domain.product.Product
import com.test.application.local_data.favourite_item.FeedbackEntity
import com.test.application.local_data.favourite_item.InfoEntity
import com.test.application.local_data.favourite_item.PriceEntity
import com.test.application.local_data.favourite_item.ProductEntity
import com.test.application.local_data.favourite_item.ProductWithDetails
import com.test.application.local_data.user_info.UserInfoEntity

fun UserLogin.toEntity(): UserInfoEntity {
    return UserInfoEntity(
        id = this.id,
        name = this.name,
        secondName = this.secondName,
        phoneNumber = this.phoneNumber
    )
}

fun UserInfoEntity.toDomain(): UserLogin {
    return UserLogin(
        id = this.id,
        name = this.name,
        secondName = this.secondName,
        phoneNumber = this.phoneNumber
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        available = available,
        description = description,
        ingredients = ingredients,
        subtitle = subtitle,
        title = title,
        isFavourite = isFavourite,
        imageResIds = imageResIds
    )
}

fun Feedback.toEntity(productId: String): FeedbackEntity {
    return FeedbackEntity(
        productId = productId,
        count = this.count,
        rating = this.rating
    )
}

fun Info.toEntity(productId: String): InfoEntity {
    return InfoEntity(
        productId = productId,
        title = title,
        value = value
    )
}

fun Price.toEntity(productId: String): PriceEntity {
    return PriceEntity(
        productId = productId,
        discount = discount,
        price = price,
        priceWithDiscount = priceWithDiscount,
        unit = unit
    )
}

fun ProductWithDetails.toDomain(): Product {
    return Product(
        available = this.product.available,
        description = this.product.description,
        feedback = Feedback(
            count = this.feedback?.count ?: 0,
            rating = this.feedback?.rating ?: 0.0
        ),
        id = this.product.id,
        info = this.infos.map { Info(it.title, it.value) },
        ingredients = this.product.ingredients,
        price = Price(
            discount = this.price.discount,
            price = this.price.price,
            priceWithDiscount = this.price.priceWithDiscount,
            unit = this.price.unit
        ),
        subtitle = this.product.subtitle,
        title = this.product.title,
        isFavourite = this.product.isFavourite,
        imageResIds = this.product.imageResIds
    )
}