package com.apoorv.shopnow.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.apoorv.shopnow.data.CartItem
import com.apoorv.shopnow.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems
    private val _couponApplied = mutableStateOf(false)
    val couponApplied : Boolean get() = _couponApplied.value

    private val _couponMessage = mutableStateOf("")
    val couponMessage : String get() = _couponMessage.value

    fun addToCart(product: Product) {
        val updatedList = _cartItems.value.toMutableList()
        val index = updatedList.indexOfFirst {
            it.product.id == product.id
        }
        if (index != -1) {
            val item = updatedList[index]
            updatedList[index] = item.copy(
                quantity = item.quantity + 1
            )
        } else {
            updatedList.add(CartItem(product = product, quantity = 1))
        }
        _cartItems.value = updatedList
    }

    fun remove(item: CartItem) {
        _cartItems.value = _cartItems.value.filter {
            it.product.id != item.product.id
        }
    }

    fun increaseQty(item: CartItem) {
        _cartItems.value = _cartItems.value.map {
            if (it.product.id == item.product.id) {
                it.copy(quantity = it.quantity + 1)
            } else it
        }
    }

    fun decreaseQty(item: CartItem) {
        val updatedList = _cartItems.value.toMutableList()
        val index = updatedList.indexOfFirst {
            it.product.id == item.product.id
        }
        if (index != -1) {
            val cartItem = updatedList[index]

            if (cartItem.quantity > 1) {
                updatedList[index] = cartItem.copy(
                    quantity = cartItem.quantity - 1
                )
            }
        }
        _cartItems.value = updatedList
    }

    fun applyCoupon(items:List<CartItem>){
        val eligibleAmount = items
            .filter { !it.product.isDiscounted() }
            .sumOf { it.product.originalPrice*it.quantity }

        if (eligibleAmount < 1000){
            _couponApplied.value= false
            _couponMessage.value = "Minimum cart value ₹1000 required"
            return
        }
        _couponApplied.value = true
        _couponMessage.value = "Coupon applied successfully"
    }

    fun removeCoupon(){
        _couponApplied.value = false
        _couponMessage.value = ""
    }
    fun clearCart(){
        _cartItems.value = emptyList()
        removeCoupon()
    }
}