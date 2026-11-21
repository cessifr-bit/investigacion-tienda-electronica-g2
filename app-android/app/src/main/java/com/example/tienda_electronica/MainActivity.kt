package com.example.tienda_electronica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

// Elementos visuales de Jetpack Compose.
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel

// ViewModel para administrar productos y carrito.
import com.example.tienda_electronica.data.TiendaViewModel

// Administrador de la sesión del usuario.
import com.example.tienda_electronica.data.SesionManager

// Elementos gráficos.
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Pantallas principales.
import com.example.tienda_electronica.ui.home.HomeScreen
import com.example.tienda_electronica.ui.cart.CarritoScreen
import com.example.tienda_electronica.ui.profile.PerfilScreen

// Colores del diseño.
import com.example.tienda_electronica.ui.theme.AzulNoche
import com.example.tienda_electronica.ui.theme.AzulSuperficie
import com.example.tienda_electronica.ui.theme.AzulBrillante
import com.example.tienda_electronica.ui.theme.AzulCielo
import com.example.tienda_electronica.ui.theme.GrisAzulado
import com.example.tienda_electronica.ui.theme.Tienda_electronicaTheme

// Actividad principal que inicia la aplicación.
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite utilizar toda el área disponible de la pantalla.
        enableEdgeToEdge()

        // Establece el tema y carga la navegación.
        setContent {
            Tienda_electronicaTheme {
                AppNavigation()
            }
        }
    }
}

// Controla las pantallas y los permisos de navegación.
@Composable
fun AppNavigation() {

    // ViewModel compartido para productos y carrito.
    val tiendaViewModel: TiendaViewModel = viewModel()

    // Comprueba si existe una sesión activa.
    var sesionActiva by remember {
        androidx.compose.runtime.mutableStateOf(
            SesionManager.haySesionActiva()
        )
    }

    // Comprueba si el usuario tiene rol de administrador.
    var esAdmin by remember {
        androidx.compose.runtime.mutableStateOf(
            SesionManager.esAdministrador()
        )
    }

    // Guarda la pantalla seleccionada.
    var selectedScreen by remember {
        mutableIntStateOf(0)
    }

    // Guarda la categoría seleccionada.
    var selectedCategory by remember {
        androidx.compose.runtime.mutableStateOf("Todos")
    }

    // Guarda el texto de búsqueda.
    var selectedQuery by remember {
        androidx.compose.runtime.mutableStateOf("")
    }

    // Estructura principal de la aplicación.
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = AzulNoche,

        // La barra inferior solamente aparece con sesión activa.
        bottomBar = {
            if (sesionActiva) {
                AppBottomNavigation(
                    selectedItem = selectedScreen,
                    esAdmin = esAdmin,
                    onItemSelected = { pantalla ->

                        // Impide que un cliente entre a Gestión.
                        if (pantalla != 2 || esAdmin) {
                            selectedScreen = pantalla
                        }
                    }
                )
            }
        }

    ) { innerPadding ->

        // Contenedor del contenido de las pantallas.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(AzulNoche)
        ) {

            // Si no existe sesión, solamente se muestra el login.
            if (!sesionActiva) {

                PerfilScreen(
                    onLoginSuccess = {

                        // Actualiza el rol del usuario autenticado.
                        esAdmin = SesionManager.esAdministrador()

                        // Habilita el acceso a la tienda.
                        sesionActiva = true
                        selectedScreen = 0
                    }
                )

            } else {

                // Muestra las pantallas según la opción seleccionada.
                when (selectedScreen) {

                    // Inicio de la tienda.
                    0 -> {
                        HomeScreen(
                            onCatalogClick = {
                                selectedCategory = "Todos"
                                selectedScreen = 1
                            },
                            onCategoryClick = { category ->
                                selectedCategory = category
                                selectedScreen = 1
                            },
                            onCartClick = {
                                selectedScreen = 4
                            },
                            onAddProduct = { producto ->

                                // Agrega el producto al carrito.
                                tiendaViewModel.agregarAlCarrito(producto)
                                selectedScreen = 4
                            },
                            onSearchSubmit = { query ->

                                // Guarda la búsqueda y abre el catálogo.
                                selectedQuery = query
                                selectedCategory = "Todos"
                                selectedScreen = 1
                            },
                            showBottomNavigation = false
                        )
                    }

                    // Catálogo de productos.
                    1 -> {
                        Catalogo(
                            initialCategory = selectedCategory,
                            initialQuery = selectedQuery
                        )
                    }

                    // Gestión exclusiva para administradores.
                    2 -> {
                        if (esAdmin && SesionManager.esAdministrador()) {
                            CrudApi()
                        } else {
                            selectedScreen = 0
                        }
                    }

                    // Perfil y cierre de sesión.
                    3 -> {
                        PerfilScreen(
                            onLogout = {

                                // Actualiza el estado de navegación.
                                sesionActiva = false
                                esAdmin = false
                                selectedScreen = 0
                            }
                        )
                    }

                    // Carrito de compras.
                    4 -> {
                        CarritoScreen(
                            onContinueShopping = {
                                selectedScreen = 1
                            }
                        )
                    }

                    // Si se selecciona una pantalla inválida, vuelve a Inicio.
                    else -> {
                        selectedScreen = 0
                    }
                }
            }
        }
    }
}

// Barra de navegación inferior.
@Composable
fun AppBottomNavigation(
    selectedItem: Int,
    esAdmin: Boolean,
    onItemSelected: (Int) -> Unit
) {

    // Opciones disponibles según el rol del usuario.
    val items = buildList {
        add(0 to "Inicio")
        add(1 to "Catálogo")

        // Gestión solamente aparece para administradores.
        if (esAdmin) {
            add(2 to "Gestión")
        }

        add(3 to "Perfil")
    }

    // Superficie que contiene las opciones.
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .border(
                width = 1.dp,
                color = GrisAzulado.copy(alpha = 0.35f),
                shape = RoundedCornerShape(18.dp)
            ),
        color = AzulSuperficie,
        shape = RoundedCornerShape(18.dp),
        shadowElevation = 5.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Recorre las opciones disponibles.
            items.forEach { (screenIndex, title) ->

                // Comprueba si la opción está seleccionada.
                val selected = selectedItem == screenIndex

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            onItemSelected(screenIndex)
                        }
                        .padding(vertical = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    // Dibuja el icono correspondiente.
                    AppNavigationIcon(
                        type = screenIndex,
                        selected = selected
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    // Nombre de la sección.
                    Text(
                        text = title,
                        color = if (selected) AzulCielo else GrisAzulado,
                        fontSize = 10.sp,
                        fontWeight = if (selected) {
                            FontWeight.SemiBold
                        } else {
                            FontWeight.Normal
                        }
                    )
                }
            }
        }
    }
}

// Dibuja los iconos personalizados de la navegación.
@Composable
fun AppNavigationIcon(
    type: Int,
    selected: Boolean
) {

    // Cambia el color según la selección.
    val color = if (selected) {
        AzulBrillante
    } else {
        GrisAzulado
    }

    Canvas(
        modifier = Modifier.size(22.dp)
    ) {

        // Ajusta las medidas del dibujo.
        val scale = size.width / 24f
        val strokeWidth = 1.8f * scale

        // Selecciona el icono que se debe dibujar.
        when (type) {

            // Icono de Inicio: una casa.
            0 -> {

                val house = Path().apply {
                    moveTo(3f * scale, 11f * scale)
                    lineTo(12f * scale, 3f * scale)
                    lineTo(21f * scale, 11f * scale)

                    moveTo(5f * scale, 10f * scale)
                    lineTo(5f * scale, 21f * scale)
                    lineTo(19f * scale, 21f * scale)
                    lineTo(19f * scale, 10f * scale)

                    moveTo(10f * scale, 21f * scale)
                    lineTo(10f * scale, 14f * scale)
                    lineTo(14f * scale, 14f * scale)
                    lineTo(14f * scale, 21f * scale)
                }

                drawPath(
                    path = house,
                    color = color,
                    style = Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round
                    )
                )
            }

            // Icono de Catálogo: cuatro cuadros.
            1 -> {

                for (row in 0..1) {
                    for (column in 0..1) {

                        drawRoundRect(
                            color = color,
                            topLeft = Offset(
                                (4f + column * 9f) * scale,
                                (4f + row * 9f) * scale
                            ),
                            size = Size(
                                7f * scale,
                                7f * scale
                            ),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(
                                1.5f * scale
                            ),
                            style = Stroke(width = strokeWidth)
                        )
                    }
                }
            }

            // Icono de Gestión: un cubo.
            2 -> {

                val cube = Path().apply {

                    moveTo(12f * scale, 3f * scale)
                    lineTo(21f * scale, 8f * scale)
                    lineTo(12f * scale, 13f * scale)
                    lineTo(3f * scale, 8f * scale)
                    close()

                    moveTo(3f * scale, 8f * scale)
                    lineTo(3f * scale, 18f * scale)
                    lineTo(12f * scale, 22f * scale)

                    moveTo(21f * scale, 8f * scale)
                    lineTo(21f * scale, 18f * scale)
                    lineTo(12f * scale, 22f * scale)

                    moveTo(12f * scale, 13f * scale)
                    lineTo(12f * scale, 22f * scale)
                }

                drawPath(
                    path = cube,
                    color = color,
                    style = Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round
                    )
                )
            }

            // Icono de Perfil: una persona.
            3 -> {

                // Cabeza.
                drawCircle(
                    color = color,
                    radius = 4f * scale,
                    center = Offset(
                        12f * scale,
                        7f * scale
                    ),
                    style = Stroke(width = strokeWidth)
                )

                // Cuerpo.
                val person = Path().apply {
                    moveTo(4f * scale, 21f * scale)

                    cubicTo(
                        4f * scale,
                        14f * scale,
                        20f * scale,
                        14f * scale,
                        20f * scale,
                        21f * scale
                    )
                }

                drawPath(
                    path = person,
                    color = color,
                    style = Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round
                    )
                )
            }
        }
    }
}

// Vista previa de la pantalla principal.
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    Tienda_electronicaTheme {
        HomeScreen()
    }
}
