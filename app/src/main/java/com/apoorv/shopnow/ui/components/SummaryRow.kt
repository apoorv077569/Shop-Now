package com.apoorv.shopnow.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SummaryRow(
    label: String,
    amount: Double,
    isDiscount: Boolean = false,
    isTotal: Boolean = false
) {
    val textColor = when {
        isTotal -> Color.Black
        isDiscount -> Color(0xFFD32F2F) // red
        else -> Color.DarkGray
    }

    val fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Medium

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            color = textColor,
            fontWeight = fontWeight
        )
        Text(
            text = "₹${"%.2f".format(amount)}",
            color = textColor,
            fontWeight = fontWeight
        )
    }
}
