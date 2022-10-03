package com.example.androidscannerexample

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductsScreen(barCode: String) {
    Text(text = "ProductsScreen: barCode: $barCode")
}
