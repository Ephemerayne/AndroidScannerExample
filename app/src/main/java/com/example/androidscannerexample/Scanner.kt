package com.example.androidscannerexample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ScannerTypeParameters(
    val title: String,
    val hint: String,
    val onScanned: (String) -> Unit,
    val action: (@Composable () -> Unit)? = null,
)

@Composable
fun ScannerScreen(scannerTypeParameters: List<ScannerTypeParameters>) {
    ScannerScreenBody(scannerTypeParameters)
}

@Composable
fun ScannerScreenBody(scannerTypeParameters: List<ScannerTypeParameters>) {
    val currentType = remember { mutableStateOf(scannerTypeParameters.first()) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyRow {
            items(scannerTypeParameters) { params ->
                ScannerTypeItem(
                    scannerTypeParameters = params,
                    onTypeItemClick = { currentType.value = it },
                    isSelected = params == currentType.value,
                )
            }
        }
        Box(modifier = Modifier.height(32.dp))
        BarCodeInput { currentType.value.onScanned(it) }
        Box(modifier = Modifier.height(32.dp))
        if (currentType.value.action != null) {
            currentType.value.action!!.invoke()
        }
    }
}

@Composable
fun ScannerTypeItem(
    scannerTypeParameters: ScannerTypeParameters,
    onTypeItemClick: (ScannerTypeParameters) -> Unit,
    isSelected: Boolean,
) {

    Box(
        modifier = Modifier
            .height(60.dp)
            .clickable { onTypeItemClick(scannerTypeParameters) }
            .background(if (isSelected) Color.Gray else Color.Transparent),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = scannerTypeParameters.title,
        )
    }
}

@Composable
fun BarCodeInput(
    onBarCodeScanned: (String) -> Unit,
) {
    val input = remember { mutableStateOf("") }

    TextField(
        value = input.value,
        placeholder = { BarCodeInputPlaceholder() },
        onValueChange = {
            input.value = it
            if (it.length > 3) {
                onBarCodeScanned(it)
            }
        }
    )
}

@Composable
fun BarCodeInputPlaceholder() {
    Text(text = "type barcode")
}
