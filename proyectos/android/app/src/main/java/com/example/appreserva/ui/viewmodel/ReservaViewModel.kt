package com.example.appreserva.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appreserva.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

data class ReservaUiState(
    val negocios: List<Negocio> = emptyList(),
    val reservas: List<Reserva> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ReservaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ReservaUiState())
    val uiState: StateFlow<ReservaUiState> = _uiState.asStateFlow()

    init {
        cargarDatosIniciales()
    }

    private fun cargarDatosIniciales() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // Simulamos carga de datos
                val negocios = listOf(
                    Negocio(
                        nombre = "Peluquería Bella",
                        ubicacion = "Calle Sol 12",
                        descripcion = "Cortes y peinados modernos",
                        telefono = "123456789",
                        web = "www.bella.com",
                        horario = "L-V: 9:00-20:00",
                        categoria = Categoria.PELUQUERIA,
                        valoracion = 4.5f,
                        numValoraciones = 120
                    ),
                    Negocio(
                        nombre = "Uñas Glam",
                        ubicacion = "Avenida Luna 5",
                        descripcion = "Manicura y pedicura profesional",
                        telefono = "987654321",
                        web = "www.glam.com",
                        horario = "L-S: 10:00-21:00",
                        categoria = Categoria.SALON_DE_UÑAS,
                        valoracion = 4.8f,
                        numValoraciones = 85
                    ),
                    Negocio(
                        nombre = "Restaurante Sabor",
                        ubicacion = "Plaza Mayor 1",
                        descripcion = "Comida casera tradicional",
                        telefono = "555666777",
                        web = "www.sabor.com",
                        horario = "L-D: 12:00-23:00",
                        categoria = Categoria.RESTAURANTE,
                        valoracion = 4.2f,
                        numValoraciones = 200
                    )
                )
                _uiState.value = _uiState.value.copy(
                    negocios = negocios,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al cargar los datos: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun crearReserva(
        negocioId: String,
        nombreCliente: String,
        telefonoCliente: String,
        emailCliente: String,
        fechaHora: LocalDateTime,
        numPersonas: Int,
        notas: String
    ) {
        viewModelScope.launch {
            try {
                val nuevaReserva = Reserva(
                    negocioId = negocioId,
                    nombreCliente = nombreCliente,
                    telefonoCliente = telefonoCliente,
                    emailCliente = emailCliente,
                    fechaHora = fechaHora,
                    numPersonas = numPersonas,
                    notas = notas
                )
                _uiState.value = _uiState.value.copy(
                    reservas = _uiState.value.reservas + nuevaReserva
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al crear la reserva: ${e.message}"
                )
            }
        }
    }

    fun cancelarReserva(reservaId: String) {
        viewModelScope.launch {
            try {
                val reservasActualizadas = _uiState.value.reservas.map { reserva ->
                    if (reserva.id == reservaId) {
                        reserva.copy(estado = EstadoReserva.CANCELADA)
                    } else {
                        reserva
                    }
                }
                _uiState.value = _uiState.value.copy(reservas = reservasActualizadas)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al cancelar la reserva: ${e.message}"
                )
            }
        }
    }
} 