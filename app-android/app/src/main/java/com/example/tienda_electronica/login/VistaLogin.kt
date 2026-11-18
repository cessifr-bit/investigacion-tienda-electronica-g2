package com.example.tienda_electronica.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tienda_electronica.data.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.http.Body
import retrofit2.http.POST

// Modelo para la petición de login (ajusta los campos según tu API backend)
data class LoginRequest(
    val email: String,
    val password: String
)

// Modelo para la respuesta de la API (debe incluir el rol, ej: "ADMIN" o "CLIENTE")
data class LoginResponse(
    val mensaje: String?,
    val rol: String?, // "ADMIN" o "CLIENTE"
    val token: String?
)

interface AuthApi {
    @POST("login") // Reemplaza con tu endpoint real de login si es distinto
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // Almacena el rol del usuario autenticado ("ADMIN" o "CLIENTE")
    var userRole by mutableStateOf<String?>(null)
        private set

    fun onEmailChange(newEmail: String) { email = newEmail }
    fun onPasswordChange(newPass: String) { password = newPass }

    fun login(onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val api = RetrofitClient.retrofitInstance.create(AuthApi::class.java)
                val response = api.login(LoginRequest(email, password))

                val rolObtenido = response.rol ?: "CLIENTE"
                userRole = rolObtenido
                onSuccess(rolObtenido)
            } catch (e: Exception) {
                errorMessage = "Error de conexión o credenciales inválidas: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

    fun cerrarSesion() {
        userRole = null
        email = ""
        password = ""
    }
}