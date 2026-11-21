package service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.mindrot.jbcrypt.BCrypt
import repository.UsuarioAuth
import java.util.Date

class AuthService {

    private val jwtSecret = System.getenv("JWT_SECRET")
        ?: "clave-temporal-solo-desarrollo-cambiar-antes-de-entregar"

    private val algoritmo = Algorithm.HMAC256(jwtSecret)

    private val emisor = "tienda-electronica-api"

    // Encriptar contraseña antes de guardarla
    fun encriptarPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt(12))
    }

    // Verificar contraseña durante el login
    fun verificarPassword(
        password: String,
        passwordHash: String
    ): Boolean {
        return try {
            BCrypt.checkpw(password, passwordHash)
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    // Generar token JWT
    fun generarToken(usuario: UsuarioAuth): String {
        val ahora = Date()
        val expiracion = Date(ahora.time + 24 * 60 * 60 * 1000)

        return JWT.create()
            .withIssuer(emisor)
            .withSubject(usuario.idUsuario.toString())
            .withClaim("email", usuario.email)
            .withClaim("rol", usuario.rol)
            .withIssuedAt(ahora)
            .withExpiresAt(expiracion)
            .sign(algoritmo)
    }

    // Validar token JWT
    fun validarToken(token: String) =
        JWT.require(algoritmo)
            .withIssuer(emisor)
            .build()
            .verify(token)
}
