/*
package com.apoorv.shopnow.utils

import com.apoorv.shopnow.data.CartItem

object PriceCalculator{
    fun subTotal(items:List<CartItem>) =
        items.sumOf { it.product.finalPrice() * it.quantity }

    fun tax(items:List<CartItem>) =
        items.sumOf {
            val price = it.product.finalPrice() * it.quantity
            price * it.product.taxPercent / 100
        }
    fun couponDiscount(items:List<CartItem>): Double{
        val eligibleAmount = items
            .filter { !it.product.isDiscounted() }
            .sumOf { it.product.originalPrice * it.quantity }
        if(eligibleAmount < 1000) return  0.0
        return minOf(eligibleAmount * 0.20,300.0)
    }
}
 */

package com.apoorv.shopnow.utils

import com.apoorv.shopnow.data.CartItem

object PriceCalculator {

    fun subTotal(items: List<CartItem>) =
        items.sumOf { it.product.finalPrice() * it.quantity }

    fun tax(items: List<CartItem>) =
        items.sumOf {
            val price = it.product.finalPrice() * it.quantity
            price * it.product.taxPercent / 100
        }

    fun couponDiscount(items: List<CartItem>): Double {

        // ✅ ONLY non-discounted items are eligible
        val eligibleAmount = items
            .filter { !it.product.isDiscounted() }
            .sumOf { it.product.finalPrice() * it.quantity }

        // ❌ Minimum cart value rule
        if (eligibleAmount < 1000) return 0.0

        // ✅ 20% discount with max cap ₹300
        return minOf(eligibleAmount * 0.20, 300.0)
    }
    fun couponEligibleAmount(items: List<CartItem>): Double {
        return items
            .filter { !it.product.isDiscounted() }
            .sumOf { it.product.finalPrice() * it.quantity }
    }

}
