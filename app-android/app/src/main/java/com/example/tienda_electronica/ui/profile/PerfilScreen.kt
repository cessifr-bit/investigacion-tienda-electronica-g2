package com.example.tienda_electronica.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Clases para conectarnos con el backend y manejar la sesión.
import com.example.tienda_electronica.data.LoginRequest
import com.example.tienda_electronica.data.RetrofitClient
import com.example.tienda_electronica.data.SesionManager
import com.example.tienda_electronica.data.UsuarioDto

// Colores utilizados en el diseño de la aplicación.
import com.example.tienda_electronica.ui.theme.AzulNoche
import com.example.tienda_electronica.ui.theme.AzulSuperficie
import com.example.tienda_electronica.ui.theme.AzulBrillante
import com.example.tienda_electronica.ui.theme.Blanco
import com.example.tienda_electronica.ui.theme.GrisAzulado

// Herramientas para ejecutar operaciones y manejar errores.
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Pantalla para iniciar sesión y consultar los datos del usuario.
@Composable
fun PerfilScreen(
    onLoginSuccess: () -> Unit = {},
    onLogout: () -> Unit = {}
) {

    // Variables para almacenar los datos del formulario.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Recupera los datos del usuario si ya existe una sesión.
    var usuario by remember {
        mutableStateOf(SesionManager.obtenerUsuario())
    }

    // Controla el estado de carga del inicio de sesión.
    var cargando by remember { mutableStateOf(false) }

    // Guarda los mensajes de error.
    var mensaje by remember { mutableStateOf("") }

    // Permite ejecutar operaciones asíncronas.
    val scope = rememberCoroutineScope()

    // Contenedor principal de la pantalla.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulNoche)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Si no hay usuario, se muestra el formulario de acceso.
        if (usuario == null) {

            Text(
                text = "Iniciar sesión",
                color = Blanco,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Accede a tu cuenta",
                color = GrisAzulado,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Campo para introducir el correo electrónico.
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    mensaje = ""
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Correo electrónico") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Blanco,
                    unfocusedTextColor = Blanco,
                    focusedBorderColor = AzulBrillante,
                    unfocusedBorderColor = GrisAzulado,
                    focusedLabelColor = AzulBrillante,
                    unfocusedLabelColor = GrisAzulado
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Campo para introducir la contraseña.
            // La transformación oculta los caracteres escritos.
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    mensaje = ""
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Blanco,
                    unfocusedTextColor = Blanco,
                    focusedBorderColor = AzulBrillante,
                    unfocusedBorderColor = GrisAzulado,
                    focusedLabelColor = AzulBrillante,
                    unfocusedLabelColor = GrisAzulado
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Muestra los mensajes de error cuando sea necesario.
            if (mensaje.isNotBlank()) {
                Text(
                    text = mensaje,
                    color = Blanco,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            // Botón para iniciar sesión.
            Button(
                onClick = {

                    // Comprueba que los campos estén completos.
                    if (email.isBlank() || password.isBlank()) {
                        mensaje = "Completa todos los campos."
                    } else {

                        // Ejecuta la solicitud al backend.
                        scope.launch {
                            cargando = true
                            mensaje = ""

                            try {

                                // Envía las credenciales al servidor.
                                val respuesta = RetrofitClient.api.login(
                                    LoginRequest(
                                        email = email.trim(),
                                        password = password
                                    )
                                )

                                // Guarda el token y los datos del usuario.
                                SesionManager.iniciarSesion(
                                    respuesta.token,
                                    respuesta.usuario
                                )

                                // Actualiza los datos del perfil.
                                usuario = respuesta.usuario

                                // Limpia la contraseña del formulario.
                                password = ""
                                mensaje = ""

                                // Informa a MainActivity que el login fue exitoso.
                                onLoginSuccess()

                            } catch (e: HttpException) {

                                // Maneja los errores devueltos por el servidor.
                                mensaje = when (e.code()) {
                                    401 -> "Correo o contraseña incorrectos."
                                    else -> "Error del servidor: ${e.code()}"
                                }

                            } catch (e: IOException) {

                                // Error cuando no se puede conectar al backend.
                                mensaje = "No se pudo conectar con el servidor."

                            } catch (e: Exception) {

                                // Maneja otros errores inesperados.
                                mensaje = "Ocurrió un error al iniciar sesión."

                            } finally {

                                // Finaliza el estado de carga.
                                cargando = false
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                enabled = !cargando,
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulBrillante,
                    contentColor = Blanco
                ),
                shape = RoundedCornerShape(14.dp)
            ) {

                // Cambia el texto mientras se procesa el acceso.
                Text(
                    text = if (cargando) "Ingresando..." else "Iniciar sesión",
                    fontWeight = FontWeight.Bold
                )
            }

        } else {

            // Si hay una sesión activa, muestra los datos del usuario.
            Text(
                text = "Mi perfil",
                color = Blanco,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Nombre completo del usuario.
            Text(
                text = usuario!!.nombreCompleto,
                color = Blanco,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Correo electrónico del usuario.
            Text(
                text = usuario!!.email,
                color = GrisAzulado,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Rol asignado desde la base de datos.
            Text(
                text = "Rol: ${usuario!!.rol}",
                color = AzulBrillante,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Botón para cerrar la sesión.
            Button(
                onClick = {

                    // Elimina el token y los datos de la sesión.
                    SesionManager.cerrarSesion()

                    // Limpia los datos locales del perfil.
                    usuario = null
                    email = ""
                    password = ""
                    mensaje = ""

                    // Informa a MainActivity que debe volver al login.
                    onLogout()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulSuperficie,
                    contentColor = Blanco
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}
