package com.ginogipsy.myshoppinglistapp

import java.util.*

fun checkQuantity(quantity: String): Int {
    val intQuantity = quantity.toIntOrNull()
    return when {
        intQuantity == null -> 0
        else -> intQuantity
    }
}

fun formatItemName(itemName: String?): String {
    return when (itemName) {
        null -> ""
        else -> itemName.trim()
            .lowercase(Locale.ROOT)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }
}