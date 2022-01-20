package com.example.a4restapi

data class Food(
    val product: Product
)
data class Product(
    val popularity_key: Long,
    val product_name: String,
    val quantity: String
)
