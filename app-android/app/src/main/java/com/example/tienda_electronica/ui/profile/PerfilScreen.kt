package com.example.tienda_electronica.ui.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.tienda_electronica.data.LoginRequest
import com.example.tienda_electronica.data.RetrofitClient
import com.example.tienda_electronica.data.SesionManager
import com.example.tienda_electronica.ui.theme.AzulNoche
import com.example.tienda_electronica.ui.theme.AzulSuperficie
import com.example.tienda_electronica.ui.theme.AzulBrillante
import com.example.tienda_electronica.ui.theme.Blanco
import com.example.tienda_electronica.ui.theme.GrisAzulado

import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private val CyanNeon = Color(0xFF00D9FF)
private val AzulNeon = Color(0xFF087BFF)
private val FondoOscuro = Color(0xFF020B19)
private val TarjetaOscura = Color(0xFF06182D)

@Composable
fun PerfilScreen(
    onLoginSuccess: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var usuario by remember {
        mutableStateOf(SesionManager.obtenerUsuario())
    }

    var cargando by remember { mutableStateOf(false) }
    var mensaje by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        FondoOscuro,
                        Color(0xFF06172E),
                        AzulNoche,
                        FondoOscuro
                    )
                )
            )
    ) {

        // Decoración tecnológica del fondo.
        CircuitosDecorativos()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (usuario == null) {

                Spacer(modifier = Modifier.height(20.dp))

                // Logo tecnológico.
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFF073B72),
                                    Color(0xFF06172D),
                                    Color(0xFF003E66)
                                )
                            )
                        )
                        .border(
                            1.5.dp,
                            CyanNeon.copy(alpha = 0.8f),
                            RoundedCornerShape(30.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Logo de acceso",
                        tint = CyanNeon,
                        modifier = Modifier.size(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nombre de la tienda.
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "TECH",
                        color = Blanco,
                        fontSize = 31.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "STORE",
                        color = CyanNeon,
                        fontSize = 31.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Text(
                    text = "T I E N D A   E L E C T R Ó N I C A",
                    color = GrisAzulado,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Tarjeta principal de inicio de sesión.
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(27.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF082746),
                                    TarjetaOscura,
                                    Color(0xFF041326)
                                )
                            )
                        )
                        .border(
                            width = 1.5.dp,
                            brush = Brush.linearGradient(
                                listOf(
                                    CyanNeon,
                                    AzulNeon,
                                    Color(0xFF123B70)
                                )
                            ),
                            shape = RoundedCornerShape(27.dp)
                        )
                        .padding(23.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Iniciar sesión",
                        color = Blanco,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.height(9.dp))

                    Text(
                        text = "Accede a tu cuenta y continúa comprando",
                        color = GrisAzulado,
                        fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    // Campo de correo electrónico.
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            mensaje = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Correo electrónico")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = null,
                                tint = CyanNeon
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Blanco,
                            unfocusedTextColor = Blanco,
                            focusedBorderColor = CyanNeon,
                            unfocusedBorderColor = AzulNeon.copy(alpha = 0.65f),
                            focusedLabelColor = CyanNeon,
                            unfocusedLabelColor = GrisAzulado,
                            cursorColor = CyanNeon,
                            focusedContainerColor = FondoOscuro.copy(alpha = 0.45f),
                            unfocusedContainerColor = FondoOscuro.copy(alpha = 0.35f)
                        )
                    )

                    Spacer(modifier = Modifier.height(17.dp))

                    // Campo de contraseña.
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            mensaje = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Contraseña")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = null,
                                tint = CyanNeon
                            )
                        },

                        // Ojito para mostrar u ocultar la contraseña.
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisible = !passwordVisible
                                }
                            ) {
                                Text(
                                    text = if (passwordVisible) "◉" else "◎",
                                    color = CyanNeon,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        },

                        singleLine = true,

                        visualTransformation = if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),

                        shape = RoundedCornerShape(16.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Blanco,
                            unfocusedTextColor = Blanco,
                            focusedBorderColor = CyanNeon,
                            unfocusedBorderColor = AzulNeon.copy(alpha = 0.65f),
                            focusedLabelColor = CyanNeon,
                            unfocusedLabelColor = GrisAzulado,
                            cursorColor = CyanNeon,
                            focusedContainerColor = FondoOscuro.copy(alpha = 0.45f),
                            unfocusedContainerColor = FondoOscuro.copy(alpha = 0.35f)
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Mensajes de error.
                    if (mensaje.isNotBlank()) {
                        Text(
                            text = mensaje,
                            color = Color(0xFFFF7777),
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    // Botón de inicio de sesión.
                    Button(
                        onClick = {
                            if (email.isBlank() || password.isBlank()) {
                                mensaje = "Completa todos los campos."
                            } else {
                                scope.launch {
                                    cargando = true
                                    mensaje = ""

                                    try {
                                        val respuesta = RetrofitClient.api.login(
                                            LoginRequest(
                                                email = email.trim(),
                                                password = password
                                            )
                                        )

                                        SesionManager.iniciarSesion(
                                            respuesta.token,
                                            respuesta.usuario
                                        )

                                        usuario = respuesta.usuario
                                        password = ""
                                        passwordVisible = false
                                        mensaje = ""

                                        onLoginSuccess()

                                    } catch (e: HttpException) {
                                        mensaje = when (e.code()) {
                                            401 -> "Correo o contraseña incorrectos."
                                            else -> "Error del servidor: ${e.code()}"
                                        }

                                    } catch (e: IOException) {
                                        mensaje = "No se pudo conectar con el servidor."

                                    } catch (e: Exception) {
                                        mensaje = "Ocurrió un error al iniciar sesión."

                                    } finally {
                                        cargando = false
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        enabled = !cargando,
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AzulBrillante,
                            contentColor = Blanco,
                            disabledContainerColor = AzulBrillante.copy(alpha = 0.5f)
                        )
                    ) {
                        Text(
                            text = if (cargando) {
                                "Ingresando..."
                            } else {
                                "Iniciar sesión   →"
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "TECNOLOGÍA PARA TI",
                    color = GrisAzulado.copy(alpha = 0.7f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(15.dp))

            } else {

                // Perfil del usuario con sesión activa.
                Spacer(modifier = Modifier.height(40.dp))

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(AzulSuperficie)
                        .border(
                            1.5.dp,
                            CyanNeon,
                            RoundedCornerShape(30.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = CyanNeon,
                        modifier = Modifier.size(45.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Mi perfil",
                    color = Blanco,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(25.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(TarjetaOscura)
                        .border(
                            1.dp,
                            CyanNeon.copy(alpha = 0.65f),
                            RoundedCornerShape(24.dp)
                        )
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = usuario!!.nombreCompleto,
                        color = Blanco,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = usuario!!.email,
                        color = GrisAzulado,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Rol: ${usuario!!.rol}",
                        color = CyanNeon,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = {
                        SesionManager.cerrarSesion()
                        usuario = null
                        email = ""
                        password = ""
                        passwordVisible = false
                        mensaje = ""
                        onLogout()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AzulBrillante,
                        contentColor = Blanco
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Cerrar sesión",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Decoración tecnológica del fondo.
@Composable
private fun CircuitosDecorativos() {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val stroke = 2.dp.toPx()
        val circuitColor = CyanNeon.copy(alpha = 0.16f)

        // Circuito superior izquierdo.
        drawLine(
            circuitColor,
            androidx.compose.ui.geometry.Offset(
                0f,
                size.height * 0.12f
            ),
            androidx.compose.ui.geometry.Offset(
                size.width * 0.13f,
                size.height * 0.12f
            ),
            stroke
        )

        drawLine(
            circuitColor,
            androidx.compose.ui.geometry.Offset(
                size.width * 0.13f,
                size.height * 0.12f
            ),
            androidx.compose.ui.geometry.Offset(
                size.width * 0.20f,
                size.height * 0.07f
            ),
            stroke
        )

        drawLine(
            circuitColor,
            androidx.compose.ui.geometry.Offset(
                size.width * 0.20f,
                size.height * 0.07f
            ),
            androidx.compose.ui.geometry.Offset(
                size.width * 0.34f,
                size.height * 0.07f
            ),
            stroke
        )

        // Circuito superior derecho.
        drawLine(
            circuitColor,
            androidx.compose.ui.geometry.Offset(
                size.width,
                size.height * 0.18f
            ),
            androidx.compose.ui.geometry.Offset(
                size.width * 0.86f,
                size.height * 0.18f
            ),
            stroke
        )

        drawLine(
            circuitColor,
            androidx.compose.ui.geometry.Offset(
                size.width * 0.86f,
                size.height * 0.18f
            ),
            androidx.compose.ui.geometry.Offset(
                size.width * 0.79f,
                size.height * 0.23f
            ),
            stroke
        )

        // Puntos luminosos.
        drawCircle(
            color = CyanNeon.copy(alpha = 0.35f),
            radius = 4.dp.toPx(),
            center = androidx.compose.ui.geometry.Offset(
                size.width * 0.34f,
                size.height * 0.07f
            )
        )

        drawCircle(
            color = CyanNeon.copy(alpha = 0.35f),
            radius = 4.dp.toPx(),
            center = androidx.compose.ui.geometry.Offset(
                size.width * 0.79f,
                size.height * 0.23f
            )
        )

        // Líneas inferiores.
        drawLine(
            circuitColor,
            androidx.compose.ui.geometry.Offset(
                0f,
                size.height * 0.88f
            ),
            androidx.compose.ui.geometry.Offset(
                size.width * 0.16f,
                size.height * 0.88f
            ),
            stroke
        )

        drawLine(
            circuitColor,
            androidx.compose.ui.geometry.Offset(
                size.width * 0.84f,
                size.height * 0.91f
            ),
            androidx.compose.ui.geometry.Offset(
                size.width,
                size.height * 0.91f
            ),
            stroke
        )
    }
}
