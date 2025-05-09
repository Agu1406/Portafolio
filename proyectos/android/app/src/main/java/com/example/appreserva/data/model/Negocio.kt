package com.example.appreserva.data.model

import java.util.UUID

data class Negocio(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val ubicacion: String,
    val descripcion: String,
    val telefono: String,
    val web: String,
    val horario: String,
    val categoria: Categoria,
    val imagenUrl: String = "",
    val valoracion: Float = 0f,
    val numValoraciones: Int = 0
)

enum class Categoria {
    PELUQUERIA,
    RESTAURANTE,
    SALON_DE_UÑAS,
    SPA,
    OTRO
} 