package com.ginogipsy.myshoppinglistapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri

@SuppressLint("QueryPermissionsNeeded")
fun Context.openGoogleMaps(lat: String, lng: String, label: String = "Destination") {
    // Schema URI per Google Maps: q=lat,lng(Label)
    val uri = "geo:0,0?q=$lat,$lng($label)".toUri()
    val mapIntent = Intent(Intent.ACTION_VIEW, uri).apply {
        // Impost the package to force Google Maps (optional)
        setPackage("com.google.android.apps.maps")
    }

    // Check existing app to manage the Intent
    if (mapIntent.resolveActivity(packageManager) != null) {
        startActivity(mapIntent)
    } else {
        // Fallback: Open the browser if Maps is not installed
        val browserUri = "https://www.google.com/".toUri()
        startActivity(Intent(Intent.ACTION_VIEW, browserUri))
    }
}

@Composable
fun MyMapButton() {
    val context = LocalContext.current // Get context here

    IconButton(onClick = {
        // Call the external function as a context function
        context.openGoogleMaps(
            lat = "41.8902",
            lng = "12.4922",
            label = "Colosseum"
        )
    }) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "See on the map",
            //tint = Color(0xFF4285F4) // blu color of Google
            tint = Color.Red // Typical color Maps's pin
        )
    }
}