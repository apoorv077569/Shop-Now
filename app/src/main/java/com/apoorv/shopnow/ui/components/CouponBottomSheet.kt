package com.apoorv.shopnow.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apoorv.shopnow.data.CartItem
import com.apoorv.shopnow.utils.PriceCalculator
import com.apoorv.shopnow.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CouponBottomSheet(
    vm: CartViewModel,
    items: List<CartItem>,
    onDismiss: () -> Unit
) {
    var couponCode by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val hasDiscountedItems = items.any { it.product.isDiscounted() }
    val eligibleAmount = PriceCalculator.couponEligibleAmount(items)
    val isCouponEnabled = eligibleAmount >= 1000

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                "Apply Coupon",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "Use SAVE20 to get 20% OFF",
                fontSize = 13.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(16.dp))
//            OutlinedTextField(
//                value = couponCode,
//                onValueChange = {
//                    couponCode = it.uppercase()
//                    isError = false
//                                },
//                placeholder = { Text("Enter coupon code") },
//                modifier = Modifier.fillMaxWidth(),
//                singleLine = true,
//                shape = RoundedCornerShape(12.dp),
//                supportingText = {
//                    if (isError){
//                        Text(
//                            "Invalid coupon code",
//                            color = MaterialTheme.colorScheme.error
//                        )
//                    }
//                }
//            )
//            Spacer(Modifier.height(8.dp))
//            when {
//                hasDiscountedItems -> {
//                    Text(
//                        text = "Coupon is not applicable on discounted items",
//                        color = Color(0xFFD32F2F),
//                        fontSize = 12.sp
//                    )
//                }
//
//                eligibleAmount < 1000 -> {
//                    Text(
//                        text = "Add ₹${1000 - eligibleAmount} more to apply coupon",
//                        color = Color.Red,
//                        fontSize = 12.sp
//                    )
//                }
//            }
//            Spacer(Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    if (couponCode == "SAVE20") {
//                        vm.applyCoupon(items)
//                        Toast.makeText(context,"Coupon Applied Successfully", Toast.LENGTH_SHORT).show()
//                        onDismiss()
//                    }else {
//                        isError = true
//                        Toast.makeText(context, "Invalid Coupon", Toast.LENGTH_SHORT).show()
//                    }
//                },
//                enabled = eligibleAmount >= 1000 && !hasDiscountedItems,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(52.dp),
//                shape = RoundedCornerShape(50)
//            ) {
//                Text("Apply Coupon")
//            }
            if (hasDiscountedItems) {

                // ❌ Coupon blocked UI
                Text(
                    text = "Coupon is not applicable on discounted items",
                    color = Color(0xFFD32F2F),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

            } else {

                // ✅ Coupon allowed UI
                OutlinedTextField(
                    value = couponCode,
                    onValueChange = {
                        couponCode = it.uppercase()
                        isError = false
                    },
                    placeholder = { Text("Enter coupon code") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    supportingText = {
                        if (isError) {
                            Text(
                                "Invalid coupon code",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(Modifier.height(8.dp))

                if (eligibleAmount < 1000) {
                    Text(
                        text = "Add ₹${1000 - eligibleAmount} more to apply coupon",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (couponCode == "SAVE20") {
                            vm.applyCoupon(items)
                            Toast.makeText(
                                context,
                                "Coupon Applied Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            onDismiss()
                        } else {
                            isError = true
                            Toast.makeText(
                                context,
                                "Invalid Coupon",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    enabled = eligibleAmount >= 1000,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Apply Coupon")
                }
            }

            Spacer(Modifier.height(12.dp))
            Text(
                ". Min cart ₹1000\n. Max discount ₹300\n. Not applicable on discounted items",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}
