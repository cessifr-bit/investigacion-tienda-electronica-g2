package service

import repository.PedidoCreado
import repository.PedidoRepository
import repository.PedidoResumen
import repository.DetallePedido
import repository.ProductoPedido

class PedidoService(
    private val pedidoRepository: PedidoRepository = PedidoRepository()
) {

    // Procesar una compra del usuario autenticado
    fun registrarPedido(
        idUsuario: Int,
        detalles: List<ProductoPedido>
    ): PedidoCreado {

        if (idUsuario <= 0) {
            throw IllegalArgumentException("Usuario no válido")
        }

        if (detalles.isEmpty()) {
            throw IllegalArgumentException("El carrito está vacío")
        }

        if (detalles.size > 100) {
            throw IllegalArgumentException("El pedido supera el límite de productos")
        }

        if (detalles.any { it.cantidad <= 0 }) {
            throw IllegalArgumentException("Las cantidades deben ser mayores que cero")
        }

        if (detalles.any { it.idProducto <= 0 }) {
            throw IllegalArgumentException("Hay productos con identificadores inválidos")
        }

        if (detalles.map { it.idProducto }.distinct().size != detalles.size) {
            throw IllegalArgumentException("Hay productos repetidos en el pedido")
        }

        return pedidoRepository.crearPedido(idUsuario, detalles)
    }

    // Listar pedidos para el administrador
    fun listarPedidos(): List<PedidoResumen> {
        return pedidoRepository.listarPedidos()
    }

    // Consultar detalles de un pedido
    fun obtenerDetalles(idPedido: Int): List<DetallePedido> {

        if (idPedido <= 0) {
            throw IllegalArgumentException("ID de pedido no válido")
        }

        return pedidoRepository.obtenerDetalles(idPedido)
    }

    // Cambiar el estado de un pedido
    fun actualizarEstado(idPedido: Int, estado: String): Boolean {

        if (idPedido <= 0) {
            throw IllegalArgumentException("ID de pedido no válido")
        }

        val estadosPermitidos = setOf(
            "PENDIENTE",
            "PAGADO",
            "ENVIADO",
            "ENTREGADO",
            "CANCELADO"
        )

        val estadoNormalizado = estado.trim().uppercase()

        if (estadoNormalizado !in estadosPermitidos) {
            throw IllegalArgumentException("El estado del pedido no es válido")
        }

        return pedidoRepository.actualizarEstado(
            idPedido,
            estadoNormalizado
        )
    }
}
