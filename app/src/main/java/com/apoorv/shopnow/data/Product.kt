package com.apoorv.shopnow.data

data class Product(
    val id: Int,
    val image: Int,
    val name: String,
    val originalPrice: Double,
    val discountedPrice: Double?,
    val taxPercent: Int
) {
    fun finalPrice() = discountedPrice ?: originalPrice
    fun isDiscounted() = discountedPrice != null
}
