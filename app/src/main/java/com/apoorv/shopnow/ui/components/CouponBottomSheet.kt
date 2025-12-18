package com.apoorv.shopnow.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apoorv.shopnow.data.CartItem
import com.apoorv.shopnow.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CouponBottomSheet(
    vm: CartViewModel,
    items: List<CartItem>,
    onDismiss: () -> Unit
) {
    var couponCode by remember { mutableStateOf("") }
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
            OutlinedTextField(
                value = couponCode,
                onValueChange = { couponCode = it.uppercase() },
                placeholder = { Text("Enter coupon code") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    if (couponCode == "SAVE20") {
                        vm.applyCoupon(items)
                        onDismiss
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("Apply Coupon")
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
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = Color(0xFFF6F4FB)
//        ),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Column(
//            Modifier.padding(16.dp)
//        ) {
//            Text("Apply Coupon", fontWeight = FontWeight.Bold,
//                fontSize = 16.sp)
//            Spacer(Modifier.height(8.dp))
//            OutlinedTextField(
//                value = couponCode,
//                onValueChange = {couponCode=it.uppercase()},
//                placeholder = {Text("Enter Coupon code")},
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                singleLine = true
//            )
//            Spacer(Modifier.height(82.dp))
//            Button(
//                onClick = {
//                    if (couponCode=="SAVE20"){
//                        vm.applyCoupon(items)
//                    }else{
//                        vm.removeCoupon()
//                    }
//                },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(50)
//            ) {
//                Text("Apply Coupon")
//            }
//            if (vm.couponMessage.isNotEmpty()){
//                Spacer(Modifier.height(6.dp))
//                Text(
//                    vm.couponMessage,
//                    fontSize = 12.sp,
//                    color = if (vm.couponApplied) Color(0xFF2E7D32) else Color.Red
//                )
//            }
//        }
}
