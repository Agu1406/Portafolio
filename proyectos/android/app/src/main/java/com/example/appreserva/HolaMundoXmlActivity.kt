package com.example.appreserva

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Ejercicio 1.1 - Hola Mundo en XML
 * Muestra un texto "Hola Mundo" usando un layout XML clásico.
 * @author Ana, Manuel y Aina
 */
class HolaMundoXmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hola_mundo)
    }
} 