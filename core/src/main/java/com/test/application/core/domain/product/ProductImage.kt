package com.test.application.core.domain.product

import com.test.application.core.R

enum class ProductImage(
    val productId: String,
    val imageRes1: Int,
    val imageRes2: Int
) {
    PRODUCT_1("cbf0c984-7c6c-4ada-82da-e29dc698bb50", R.drawable.image6, R.drawable.image5),
    PRODUCT_2("54a876a5-2205-48ba-9498-cfecff4baa6e", R.drawable.image1, R.drawable.image2),
    PRODUCT_3("75c84407-52e1-4cce-a73a-ff2d3ac031b3", R.drawable.image5, R.drawable.image6),
    PRODUCT_4("16f88865-ae74-4b7c-9d85-b68334bb97db", R.drawable.image3, R.drawable.image4),
    PRODUCT_5("26f88856-ae74-4b7c-9d85-b68334bb97db", R.drawable.image2, R.drawable.image3),
    PRODUCT_6("15f88865-ae74-4b7c-9d81-b78334bb97db", R.drawable.image6, R.drawable.image1),
    PRODUCT_7("88f88865-ae74-4b7c-9d81-b78334bb97db", R.drawable.image4, R.drawable.image3),
    PRODUCT_8("55f58865-ae74-4b7c-9d81-b78334bb97db", R.drawable.image1, R.drawable.image5);

    companion object {
        fun findImagesByProductId(id: String): Pair<Int, Int>? {
            return entries.find { it.productId == id }?.let {
                Pair(it.imageRes1, it.imageRes2)
            }
        }
    }
}