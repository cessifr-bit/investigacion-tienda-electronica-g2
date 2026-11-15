import model.Producto
import repository.ProductoRepository
import java.math.BigDecimal

fun main() {
    val repo = ProductoRepository()

    println("=== 1. LISTADO DE PRODUCTOS ACTIVOS ===")
    repo.listarActivos().forEach {
        println("[${it.codigoSku}] ${it.nombre} - $${it.precio} | Stock: ${it.stock} | Marca: ${it.nombreMarca} | Cat: ${it.nombreCategoria}")
    }

    println("\n=== 2. BÚSQUEDA POR 'Samsung' ===")
    repo.buscarPorNombreOSku("Samsung").forEach {
        println("Encontrado: ${it.nombre} ($${it.precio})")
    }

    println("\n=== 3. PRODUCTOS CON BAJO STOCK (<= 20) ===")
    repo.listarBajoStock(20).forEach {
        println("Alerta stock: ${it.nombre} -> ${it.stock} unidades")
    }

    println("\n=== 4. INSERTAR NUEVO PRODUCTO ===")
    val nuevo = Producto(
        codigoSku = "MON-ASU-009",
        nombre = "Monitor ASUS TUF Gaming 27\"",
        descripcion = "Monitor QHD 180Hz 1ms IPS",
        precio = BigDecimal("299.99"),
        stock = 12,
        imagenUrl = "https://ejemplo.com/img/tuf-27.jpg",
        idCategoria = 4,
        idMarca = 1
    )
    val nuevoId = repo.insertar(nuevo)
    println("Producto insertado con ID: $nuevoId")

    if (nuevoId > 0) {
        println("\n=== 5. DESCONTAR STOCK (-2 unidades) ===")
        val stockActualizado = repo.ajustarStock(nuevoId, -2)
        println("Stock actualizado: $stockActualizado | Nuevo stock: ${repo.obtenerPorId(nuevoId)?.stock}")

        println("\n=== 6. ELIMINACIÓN LÓGICA ===")
        val eliminado = repo.eliminarLogico(nuevoId)
        println("Producto desactivado: $eliminado")
    }
}