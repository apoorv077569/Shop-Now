package com.apoorv.shopnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apoorv.shopnow.ui.screens.CartScreen
import com.apoorv.shopnow.ui.screens.CheckoutSuccessScreen
import com.apoorv.shopnow.ui.screens.ProductScreen
import com.apoorv.shopnow.ui.screens.SplashScreen
import com.apoorv.shopnow.viewmodel.CartViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val nav = rememberNavController()
            val vm: CartViewModel = viewModel()
            NavHost(nav, startDestination = "splash") {
                composable("splash") {
                    SplashScreen {
                        nav.navigate("products") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                }
                composable("products") {
                    ProductScreen(
                        vm, goToCart = { nav.navigate("cart") })
                }
                composable("cart") {
                    CartScreen(vm, nav)
                }
                composable("success") {
                    CheckoutSuccessScreen {
                        nav.navigate("products"){
                            popUpTo("products"){inclusive=true}
                        }
                    }
                }
            }
        }
    }
}



