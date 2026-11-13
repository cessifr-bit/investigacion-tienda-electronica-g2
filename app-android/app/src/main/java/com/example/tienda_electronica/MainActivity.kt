
package com.example.tienda_electronica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.tienda_electronica.ui.home.HomeScreen
import com.example.tienda_electronica.ui.theme.AzulNoche
import com.example.tienda_electronica.ui.theme.AzulSuperficie
import com.example.tienda_electronica.ui.theme.AzulBrillante
import com.example.tienda_electronica.ui.theme.AzulCielo
import com.example.tienda_electronica.ui.theme.GrisAzulado
import com.example.tienda_electronica.ui.theme.Tienda_electronicaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Tienda_electronicaTheme {
                AppNavigation()
            }
        }
    }
}


// Controla las pantallas y la navegación principal
@Composable
fun AppNavigation() {

    // Guarda la opción seleccionada del menú
    var selectedScreen by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = AzulNoche,

        // Menú inferior compartido por todas las pantallas
        bottomBar = {
            AppBottomNavigation(
                selectedItem = selectedScreen,
                onItemSelected = { selectedScreen = it }
            )
        }

    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(AzulNoche)
        ) {

            when (selectedScreen) {

                // Pantalla principal
                0 -> {
                    HomeScreen(
                        showBottomNavigation = false
                    )
                }

                // Catálogo
                1 -> {
                    Catalogo()
                }

                // Gestión de productos
                2 -> {
                    CrudScreen()
                }

                // Perfil provisional
                3 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Perfil",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}


// Barra inferior de navegación
@Composable
fun AppBottomNavigation(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {

    val items = listOf(
        "Inicio",
        "Catálogo",
        "Gestión",
        "Perfil"
    )

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

            items.forEachIndexed { index, title ->

                val selected = selectedItem == index

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            onItemSelected(index)
                        }
                        .padding(vertical = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    // Dibuja el icono correspondiente
                    AppNavigationIcon(
                        type = index,
                        selected = selected
                    )

                    Spacer(modifier = Modifier.height(5.dp))

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


// Iconos personalizados del menú inferior
@Composable
fun AppNavigationIcon(
    type: Int,
    selected: Boolean
) {

    val color = if (selected) {
        AzulBrillante
    } else {
        GrisAzulado
    }

    Canvas(
        modifier = Modifier.size(22.dp)
    ) {

        val scale = size.width / 24f
        val strokeWidth = 1.8f * scale

        when (type) {

            // Icono de Inicio: casa
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

            // Icono de Catálogo: cuadrícula
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

            // Icono de Gestión: cubo
            2 -> {

                val cube = Path().apply {

                    // Parte superior
                    moveTo(12f * scale, 3f * scale)
                    lineTo(21f * scale, 8f * scale)
                    lineTo(12f * scale, 13f * scale)
                    lineTo(3f * scale, 8f * scale)
                    close()

                    // Lado izquierdo
                    moveTo(3f * scale, 8f * scale)
                    lineTo(3f * scale, 18f * scale)
                    lineTo(12f * scale, 22f * scale)

                    // Lado derecho
                    moveTo(21f * scale, 8f * scale)
                    lineTo(21f * scale, 18f * scale)
                    lineTo(12f * scale, 22f * scale)

                    // Línea central
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

            // Icono de Perfil: usuario
            3 -> {

                drawCircle(
                    color = color,
                    radius = 4f * scale,
                    center = Offset(
                        12f * scale,
                        7f * scale
                    ),
                    style = Stroke(width = strokeWidth)
                )

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


// Vista previa de la pantalla principal
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    Tienda_electronicaTheme {
        HomeScreen()
    }
}
