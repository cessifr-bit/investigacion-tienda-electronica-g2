package com.example.tienda_electronica.data

// Esta clase administra la sesión del usuario mientras la aplicación está abierta.
object SesionManager {

    // Guarda el token que entrega el backend al iniciar sesión.
    private var token: String? = null

    // Guarda los datos del usuario que inició sesión.
    private var usuario: UsuarioDto? = null

    // Guarda el token y los datos del usuario al iniciar sesión.
    fun iniciarSesion(nuevoToken: String, nuevoUsuario: UsuarioDto) {
        token = nuevoToken
        usuario = nuevoUsuario
    }

    // Elimina los datos de la sesión al cerrar sesión.
    fun cerrarSesion() {
        token = null
        usuario = null
    }

    // Devuelve el token para las solicitudes al backend.
    fun obtenerToken(): String? {
        return token
    }

    // Devuelve los datos del usuario que inició sesión.
    fun obtenerUsuario(): UsuarioDto? {
        return usuario
    }

    // Comprueba si existe una sesión activa.
    fun haySesionActiva(): Boolean {
        return token != null && usuario != null
    }

    // Comprueba si el usuario tiene permisos de administrador.
    fun esAdministrador(): Boolean {
        return usuario?.rol == "ADMIN"
    }
}
