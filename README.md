# ShopNow 🛒  
A Mini Shopping Cart Android App built using Jetpack Compose

---

## 📌 Overview

**ShopNow** is a modern Android shopping cart application developed using **Jetpack Compose**.  
The app demonstrates product listing, cart management, coupon application, tax calculation, and a smooth checkout flow with animations.

All data is stored **in-memory** and the app works **offline**, as required.

---

## ✨ Features

- 🚀 Splash screen with Lottie animation
- 🛍️ Product listing (in-memory)
- 💸 Pre-discounted & non-discounted products
- 🧾 Mixed tax rates (5% and 18%)
- ➕➖ Cart with quantity management
- 🎟️ Coupon popup (Modal Bottom Sheet)
  - Coupon code: **SAVE20**
  - 20% discount
  - Minimum cart value: ₹1000
  - Maximum discount cap: ₹300
  - Coupon not applicable on discounted items
- 🧮 Automatic subtotal, tax, and final amount calculation
- 🎉 Checkout with confetti animation
- ✅ Order success screen after checkout
- 🎨 Clean, modern Material 3 UI

---

## 🧑‍💻 Tech Stack

- **Language:** Kotlin  
- **UI Toolkit:** Jetpack Compose  
- **Architecture:** MVVM  
- **Design System:** Material 3  
- **Animations:** Lottie  

---

## 📱 Screenshots

| Splash Screen | Product List | Cart |
|--------------|-------------|------|
| ![Splash](screenshots/01_splash.png) | ![Products](screenshots/02_products.png) | ![Cart](screenshots/03_cart.png) |

| Coupon Popup | Checkout Success |
|-------------|------------------|
| ![Coupon](screenshots/04_coupon.png) | ![Success](screenshots/05_success.png) |

---

## 🎥 Demo Video

A demo video showcasing the complete flow:
- Product selection
- Cart updates
- Coupon application
- Checkout with animation

📎 **shopnow_demo.mp4**

---

## 📦 APK

A release APK is included for testing:

📎 **ShopNow.apk**

---

## 🧪 Coupon Logic Summary

- Coupon Code: **SAVE20**
- Discount: **20%**
- Minimum eligible cart value: **₹1000**
- Maximum discount: **₹300**
- Coupon applies **only to non-discounted items**

---

## 🚀 How to Run the Project

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run on emulator or physical device

---

## 📌 Notes

- No backend or database is used
- All data is stored in memory
- Images are optimized to prevent bitmap memory crashes
- App does not require internet access

---

## 👨‍💻 Author

**Apoorv Rathore**  
Android Developer | Jetpack Compose
