package com.example.appreserva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.tooling.preview.Preview

/**
 * Ejercicio 1.2 - Botón con Jetpack Compose
 * Muestra un botón sencillo usando Jetpack Compose.
 * @author Ana, Manuel y Aina
 */
class BotonComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BotonComposeScreen()
        }
    }
}

@Composable
fun BotonComposeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { /* Acción de ejemplo */ }) {
            Text("Púlsame")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BotonComposePreview() {
    BotonComposeScreen()
} 