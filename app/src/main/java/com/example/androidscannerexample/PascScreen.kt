package com.example.androidscannerexample

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun PacsScreen(barCode: String) {
    Text(text = "PacsScreen: barCode: $barCode")
}
