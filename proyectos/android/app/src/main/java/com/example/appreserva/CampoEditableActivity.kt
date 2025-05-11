package com.example.appreserva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.OutlinedTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

/**
 * Ejercicio 2 - Campo editable, botón y campo no editable con Jetpack Compose
 * @author Ana, Manuel y Aina
 */
class CampoEditableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CampoEditableScreen()
        }
    }
}

@Composable
fun CampoEditableScreen() {
    var texto by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Introduce un texto") }
        )
        Button(onClick = { resultado = texto }) {
            Text("Mostrar texto")
        }
        OutlinedTextField(
            value = resultado,
            onValueChange = {},
            label = { Text("Texto no editable") },
            enabled = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CampoEditablePreview() {
    CampoEditableScreen()
} 