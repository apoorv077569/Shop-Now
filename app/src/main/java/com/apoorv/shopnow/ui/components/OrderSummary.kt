package com.apoorv.shopnow.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apoorv.shopnow.data.CartItem
import com.apoorv.shopnow.utils.PriceCalculator
import com.apoorv.shopnow.viewmodel.CartViewModel

@Composable
fun OrderSummary(items: List<CartItem>,vm: CartViewModel) {
    val subTotal = PriceCalculator.subTotal(items)
    val tax = PriceCalculator.tax(items)
    val discount = if (vm.couponApplied){
        PriceCalculator.couponDiscount(items)
    } else {
        0.0
    }
    val total = subTotal + tax - discount

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6F4FB)
        ),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            SummaryRow("Subtotal", subTotal)
            SummaryRow("Tax", tax)
            SummaryRow("Coupon Discount", -discount, isDiscount = true)
            Divider(Modifier.padding(vertical = 8.dp))
            SummaryRow("Total Payable", total, isTotal = true)
        }
    }
}