package com.apoorv.shopnow.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.apoorv.shopnow.R
import com.apoorv.shopnow.viewmodel.CartViewModel

@Composable
fun CheckoutButton(
    nav: NavController,
    isCartEmpty : Boolean
) {
    var show by remember { mutableStateOf(false) }

    Button(
        onClick = {
            show=true
        },
        modifier = Modifier.fillMaxWidth()
            .height(56.dp),
        enabled = !isCartEmpty
    ) {
        Text(
            text = if (isCartEmpty) "Cart is Empty"
            else "Checkout"
        )
    }
    if (show){
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.confetti)
        )
        LottieAnimation(composition, iterations = 1)

        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(1200)
            nav.navigate("success"){
                popUpTo("cart"){inclusive=true}
            }
        }
    }
}