package com.test.application.remote_data.dto

data class ItemDTO(
    val available: Int = 0,
    val description: String = "",
    val feedback: FeedbackDTO = FeedbackDTO(),
    val id: String = "",
    val info: List<InfoDTO> = listOf(),
    val ingredients: String = "",
    val price: PriceDTO = PriceDTO(),
    val subtitle: String = "",
    val tags: List<String> = listOf(),
    val title: String = ""
)