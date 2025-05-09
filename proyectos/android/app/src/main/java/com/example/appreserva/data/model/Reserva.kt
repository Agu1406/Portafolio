package com.example.appreserva.data.model

import java.time.LocalDateTime
import java.util.UUID

data class Reserva(
    val id: String = UUID.randomUUID().toString(),
    val negocioId: String,
    val nombreCliente: String,
    val telefonoCliente: String,
    val emailCliente: String,
    val fechaHora: LocalDateTime,
    val numPersonas: Int = 1,
    val notas: String = "",
    val estado: EstadoReserva = EstadoReserva.PENDIENTE
)

enum class EstadoReserva {
    PENDIENTE,
    CONFIRMADA,
    CANCELADA,
    COMPLETADA
} 