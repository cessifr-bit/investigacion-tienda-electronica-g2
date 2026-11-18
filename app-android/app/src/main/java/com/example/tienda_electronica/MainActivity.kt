package com.example.tienda_electronica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.* // Usamos los estados de Compose
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tienda_electronica.login.LoginScreen // Importamos tu pantalla de login recién creada
import com.example.tienda_electronica.ui.theme.Tienda_electronicaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tienda_electronicaTheme {
                // Estado para almacenar el rol del usuario ("ADMIN", "CLIENTE" o null si no ha iniciado sesión)
                var userRole by remember { mutableStateOf<String?>(null) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (userRole) {
                        null -> {
                            // 1. Si no hay sesión, mostramos el Login
                            LoginScreen(
                                onLoginSuccess = { rolObtenido ->
                                    // Guardamos el rol que devuelve tu API y lo pasamos a mayúsculas por seguridad
                                    userRole = rolObtenido.uppercase()
                                }
                            )
                        }
                        "ADMIN" -> {
                            // 2. Vista de Administrador (puedes mostrar aquí tu panel de gestión)
                            // Por ahora dejamos el catálogo o una vista adaptada, con opción de cerrar sesión
                            Catalogo(modifier = Modifier.padding(innerPadding))
                        }
                        else -> {
                            // 3. Vista de Cliente (Catálogo normal de productos)[cite: 1]
                            Catalogo(modifier = Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tienda_electronicaTheme {
        Greeting("Android")
    }
}