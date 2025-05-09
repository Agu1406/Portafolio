package com.example.appreserva

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appreserva.data.model.*
import com.example.appreserva.ui.theme.AppReservaTheme
import com.example.appreserva.ui.viewmodel.ReservaViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.clickable
import com.google.android.datatransport.Event
import java.time.LocalDateTime.now

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppReservaTheme {
                ReservaFacilApp()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservaFacilApp() {
    val navController = rememberNavController()
    val viewModel: ReservaViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "lista") {
        composable("lista") {
            ListaNegocios(
                negocios = uiState.negocios,
                isLoading = uiState.isLoading,
                error = uiState.error,
                onNegocioClick = { negocio ->
                    navController.navigate("detalle/${negocio.id}")
                }
            )
        }
        
        composable("detalle/{negocioId}") { backStackEntry ->
            val negocioId = backStackEntry.arguments?.getString("negocioId")
            val negocio = uiState.negocios.find { it.id == negocioId }
            
            if (negocio != null) {
                DetalleNegocio(
                    negocio = negocio,
                    onReservar = { nombreCliente, telefono, email, fecha, personas, notas ->
                        viewModel.crearReserva(
                            negocioId = negocio.id,
                            nombreCliente = nombreCliente,
                            telefonoCliente = telefono,
                            emailCliente = email,
                            fechaHora = fecha,
                            numPersonas = personas,
                            notas = notas
                        )
                        navController.navigate("reservas")
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
        
        composable("reservas") {
            MisReservas(
                reservas = uiState.reservas,
                negocios = uiState.negocios,
                onCancelarReserva = { reservaId ->
                    viewModel.cancelarReserva(reservaId)
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun ListaNegocios(
    negocios: List<com.example.appreserva.data.model.Negocio>,
    isLoading: Boolean,
    error: String?,
    onNegocioClick: (Negocio) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Negocios disponibles") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                error != null -> {
                    Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(negocios) { negocio ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable { onNegocioClick(negocio) },
                                elevation = 4.dp
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = negocio.nombre,
                                            style = MaterialTheme.typography.h6,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Row {
                                            Icon(
                                                Icons.Default.Star,
                                                contentDescription = "Valoración",
                                                tint = Color.Yellow
                                            )
                                            Text(
                                                text = String.format("%.1f", negocio.valoracion),
                                                style = MaterialTheme.typography.subtitle1
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = negocio.descripcion,
                                        style = MaterialTheme.typography.body1
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            Icons.Default.LocationOn,
                                            contentDescription = "Ubicación",
                                            tint = MaterialTheme.colors.primary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = negocio.ubicacion,
                                            style = MaterialTheme.typography.body2
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            Icons.Default.Info,
                                            contentDescription = "Horario",
                                            tint = MaterialTheme.colors.primary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = negocio.horario,
                                            style = MaterialTheme.typography.body2
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetalleNegocio(
    negocio: Negocio,
    onReservar: (String, String, String, LocalDateTime, Int, String) -> Unit,
    onBack: () -> Unit
) {
    var nombreCliente by remember { mutableStateOf("") }
    var telefonoCliente by remember { mutableStateOf("") }
    var emailCliente by remember { mutableStateOf("") }
    var fechaSeleccionada by remember { mutableStateOf(now()) }
    var numPersonas by remember { mutableStateOf(1) }
    var notas by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(negocio.nombre) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Información del negocio",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Descripción: ${negocio.descripcion}")
                    Text("Ubicación: ${negocio.ubicacion}")
                    Text("Teléfono: ${negocio.telefono}")
                    Text("Web: ${negocio.web}")
                    Text("Horario: ${negocio.horario}")
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Valoración",
                            tint = Color.Yellow
                        )
                        Text(
                            text = String.format("%.1f (%d valoraciones)", 
                                negocio.valoracion, 
                                negocio.numValoraciones
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Realizar reserva",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedTextField(
                        value = nombreCliente,
                        onValueChange = { nombreCliente = it },
                        label = { Text("Tu nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedTextField(
                        value = telefonoCliente,
                        onValueChange = { telefonoCliente = it },
                        label = { Text("Teléfono") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedTextField(
                        value = emailCliente,
                        onValueChange = { emailCliente = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Número de personas:")
                        Row {
                            IconButton(
                                onClick = { if (numPersonas > 1) numPersonas-- }
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    "Reducir"
                                )
                            }
                            Text(
                                text = numPersonas.toString(),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            IconButton(
                                onClick = { numPersonas++ }
                            ) {
                                Icon(Icons.Default.Add, "Aumentar")
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedTextField(
                        value = notas,
                        onValueChange = { notas = it },
                        label = { Text("Notas adicionales") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = { 
                            onReservar(
                                nombreCliente,
                                telefonoCliente,
                                emailCliente,
                                fechaSeleccionada,
                                numPersonas,
                                notas
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = nombreCliente.isNotBlank() && 
                                 telefonoCliente.isNotBlank() && 
                                 emailCliente.isNotBlank()
                    ) {
                        Text("Reservar")
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MisReservas(
    reservas: List<Reserva>,
    negocios: List<Negocio>,
    onCancelarReserva: (String) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Reservas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
    ) { padding ->
        if (reservas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = "Sin reservas",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No tienes reservas",
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(reservas) { reserva ->
                    val negocio = negocios.find { it.id == reserva.negocioId }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = 4.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = negocio?.nombre ?: "Negocio no encontrado",
                                    style = MaterialTheme.typography.h6,
                                    fontWeight = FontWeight.Bold
                                )
                                when (reserva.estado) {
                                    EstadoReserva.PENDIENTE -> {
                                        Text(
                                            text = "Pendiente",
                                            color = Color(0xFFFFA500)
                                        )
                                    }
                                    EstadoReserva.CONFIRMADA -> {
                                        Text(
                                            text = "Confirmada",
                                            color = Color.Green
                                        )
                                    }
                                    EstadoReserva.CANCELADA -> {
                                        Text(
                                            text = "Cancelada",
                                            color = Color.Red
                                        )
                                    }
                                    EstadoReserva.COMPLETADA -> {
                                        Text(
                                            text = "Completada",
                                            color = Color.Blue
                                        )
                                    }
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text("Fecha: ${reserva.fechaHora.format(
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                            )}")
                            Text("Cliente: ${reserva.nombreCliente}")
                            Text("Personas: ${reserva.numPersonas}")
                            if (reserva.notas.isNotBlank()) {
                                Text("Notas: ${reserva.notas}")
                            }
                            
                            if (reserva.estado == EstadoReserva.PENDIENTE) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = { onCancelarReserva(reserva.id) },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Red
                                    )
                                ) {
                                    Text("Cancelar Reserva")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}