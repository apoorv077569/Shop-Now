package com.apoorv.shopnow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.apoorv.shopnow.ui.CheckoutButton
import com.apoorv.shopnow.ui.components.CartItemRow
import com.apoorv.shopnow.ui.components.CouponBottomSheet
import com.apoorv.shopnow.ui.components.OrderSummary
import com.apoorv.shopnow.viewmodel.CartViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    vm: CartViewModel,
    navController: NavController,
) {
    val items by vm.cartItems.collectAsState()
    var showCouponSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("My Cart") }, navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, "Back"
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6A4FB3),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (items.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text("Your Cart is Empty")
                }
                return@Column
            }
            LazyColumn(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { item ->
                    CartItemRow(
                        item = item,
                        onDecrease = { vm.decreaseQty(item) },
                        onIncrease = { vm.increaseQty(item) },
                        onRemove = { vm.remove(item) })
                }
            }
            Spacer(Modifier.height(16.dp))
//            CouponSection(vm, items)
//            Spacer(Modifier.height(5.dp))

            OrderSummary(items, vm)
            Spacer(Modifier.height(6.dp))
            OutlinedButton(
                onClick = { showCouponSheet = true },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Apply Coupon")
            }
            Spacer(Modifier.height(5.dp))
            Text(
                "Coupon: SAVE20 • 20% off • Min ₹1000 • Max ₹300 • Not on discounted items",
                fontSize = 11.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(16.dp))
//            Spacer(Modifier.height(16.dp))
            CheckoutButton(navController, false)
        }
        if (showCouponSheet){
            CouponBottomSheet(
                vm,
                items,
                onDismiss = {showCouponSheet = false}
            )
        }
    }
}

