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
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image

/**
 * Actividad principal de la aplicación de reservas.
 * Gestiona la interfaz principal y la navegación entre pantallas.
 * @author Ana, Manuel y Aina
 */
class MainActivity : ComponentActivity() {
    /**
     * Método de ciclo de vida onCreate. Inicializa la UI con Jetpack Compose.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Se aplica el tema personalizado de la app
            AppReservaTheme {
                // Llama a la función principal de la app
                ReservaFacilApp()
            }
        }
    }
}

/**
 * Composable principal que gestiona la navegación y el estado global de la app.
 * @author Ana, Manuel y Aina
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservaFacilApp() {
    // Controlador de navegación para cambiar entre pantallas
    val navController = rememberNavController()
    // ViewModel que gestiona el estado de la UI
    val viewModel: ReservaViewModel = viewModel()
    // Estado observable de la UI
    val uiState by viewModel.uiState.collectAsState()

    // Definición de las rutas de navegación
    NavHost(navController = navController, startDestination = "lista") {
        composable("lista") {
            // Pantalla de lista de negocios
            ListaNegocios(
                negocios = uiState.negocios,
                isLoading = uiState.isLoading,
                error = uiState.error,
                onNegocioClick = { negocio ->
                    // Navega a la pantalla de detalle al pulsar un negocio
                    navController.navigate("detalle/${negocio.id}")
                }
            )
        }
        
        composable("detalle/{negocioId}") { backStackEntry ->
            // Obtiene el ID del negocio seleccionado
            val negocioId = backStackEntry.arguments?.getString("negocioId")
            // Busca el negocio correspondiente
            val negocio = uiState.negocios.find { it.id == negocioId }
            
            if (negocio != null) {
                // Muestra la pantalla de detalle del negocio
                DetalleNegocio(
                    negocio = negocio,
                    onReservar = { nombreCliente, telefono, email, fecha, personas, notas ->
                        // Llama a la función para crear una reserva
                        viewModel.crearReserva(
                            negocioId = negocio.id,
                            nombreCliente = nombreCliente,
                            telefonoCliente = telefono,
                            emailCliente = email,
                            fechaHora = fecha,
                            numPersonas = personas,
                            notas = notas
                        )
                        // Navega a la pantalla de reservas
                        navController.navigate("reservas")
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
        
        composable("reservas") {
            // Pantalla de reservas del usuario
            MisReservas(
                reservas = uiState.reservas,
                negocios = uiState.negocios,
                onCancelarReserva = { reservaId ->
                    // Cancela la reserva seleccionada
                    viewModel.cancelarReserva(reservaId)
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}

/**
 * Muestra la lista de negocios disponibles para reservar.
 * Incluye manejo de carga, errores y selección de negocio.
 * @param negocios Lista de negocios a mostrar
 * @param isLoading Indica si se están cargando los datos
 * @param error Mensaje de error si ocurre alguno
 * @param onNegocioClick Acción al pulsar un negocio
 * @author Ana, Manuel y Aina
 */
@Composable
fun ListaNegocios(
    negocios: List<com.example.appreserva.data.model.Negocio>,
    isLoading: Boolean,
    error: String?,
    onNegocioClick: (Negocio) -> Unit
) {
    // Scaffold proporciona la estructura básica de la pantalla
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Negocios disponibles") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
    ) { padding ->
        // Contenedor principal de la pantalla
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                isLoading -> {
                    // Indicador de carga
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                error != null -> {
                    // Muestra el mensaje de error
                    Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                else -> {
                    // Lista de negocios
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(negocios) { negocio ->
                            // Tarjeta para cada negocio
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
                                    // Imagen del negocio según la categoría
                                    val imagenRes = when (negocio.categoria) {
                                        Categoria.PELUQUERIA -> R.drawable.peluqueria
                                        Categoria.RESTAURANTE -> R.drawable.restaurante
                                        Categoria.SALON_DE_UÑAS -> R.drawable.manicura
                                        else -> R.drawable.restaurante // Imagen por defecto
                                    }
                                    // Muestra la imagen del negocio
                                    Image(
                                        painter = painterResource(id = imagenRes),
                                        contentDescription = "Imagen del negocio",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(160.dp)
                                            .padding(bottom = 8.dp)
                                    )
                                    // Fila con el nombre y valoración
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
                                    // Descripción del negocio
                                    Text(
                                        text = negocio.descripcion,
                                        style = MaterialTheme.typography.body1
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    // Fila con la ubicación
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
                                    // Fila con el horario
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

/**
 * Muestra la pantalla de detalle de un negocio y permite realizar una reserva.
 * @param negocio Negocio a mostrar
 * @param onReservar Acción al pulsar el botón de reservar
 * @param onBack Acción al pulsar el botón de volver
 * @author Ana, Manuel y Aina
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetalleNegocio(
    negocio: Negocio,
    onReservar: (String, String, String, LocalDateTime, Int, String) -> Unit,
    onBack: () -> Unit
) {
    // Estados locales para los campos del formulario de reserva
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
            // Tarjeta con la información del negocio
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Título de la sección
                    Text(
                        text = "Información del negocio",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Descripción y datos del negocio
                    Text("Descripción: ${negocio.descripcion}")
                    Text("Ubicación: ${negocio.ubicacion}")
                    Text("Teléfono: ${negocio.telefono}")
                    Text("Web: ${negocio.web}")
                    Text("Horario: ${negocio.horario}")
                    // Fila con la valoración
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

            // Tarjeta con el formulario de reserva
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Título de la sección
                    Text(
                        text = "Realizar reserva",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Campo para el nombre del cliente
                    OutlinedTextField(
                        value = nombreCliente,
                        onValueChange = { nombreCliente = it },
                        label = { Text("Tu nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Campo para el teléfono
                    OutlinedTextField(
                        value = telefonoCliente,
                        onValueChange = { telefonoCliente = it },
                        label = { Text("Teléfono") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Campo para el email
                    OutlinedTextField(
                        value = emailCliente,
                        onValueChange = { emailCliente = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Selección del número de personas
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
                    // Campo para notas adicionales
                    OutlinedTextField(
                        value = notas,
                        onValueChange = { notas = it },
                        label = { Text("Notas adicionales") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Botón para reservar
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

/**
 * Muestra la lista de reservas del usuario y permite cancelarlas.
 * @param reservas Lista de reservas
 * @param negocios Lista de negocios para mostrar información
 * @param onCancelarReserva Acción al cancelar una reserva
 * @param onBack Acción al pulsar el botón de volver
 * @author Ana, Manuel y Aina
 */
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
            // Muestra mensaje si no hay reservas
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
            // Lista de reservas
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(reservas) { reserva ->
                    // Busca el negocio correspondiente a la reserva
                    val negocio = negocios.find { it.id == reserva.negocioId }
                    // Tarjeta de reserva
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
                                // Estado de la reserva
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
                            // Información de la reserva
                            Text("Fecha: ${reserva.fechaHora.format(
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                            )}")
                            Text("Cliente: ${reserva.nombreCliente}")
                            Text("Personas: ${reserva.numPersonas}")
                            if (reserva.notas.isNotBlank()) {
                                Text("Notas: ${reserva.notas}")
                            }
                            // Botón para cancelar si la reserva está pendiente
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