package com.example.androidscannerexample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class ItemsParams(
    val title: String,
    val desc: String
)

data class ButtonSettings<T>(
    val onClick: (T?) -> Unit,
)

val items = listOf(ItemsParams("title", "desc"), ItemsParams("title2", "desc2"))

@Composable
fun AnotherFeature(
    barCode: String,
    buttonSettings: Pair<ButtonSettings<ItemsParams>, ButtonSettings<ItemsParams>>
) {
    LazyColumn {
        items(items) {
           Card(modifier = Modifier.clickable { buttonSettings.first.onClick(it) }) {
               Text(modifier = Modifier.clickable { buttonSettings.second.onClick(null) },
                   text = it.title)
               Text(text = it.desc)
           }
        }
    }
}
