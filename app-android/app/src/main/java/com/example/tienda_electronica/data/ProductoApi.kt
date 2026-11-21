package com.example.tienda_electronica.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductoApi {

    @GET("productos")
    suspend fun listar(
        @Query("q") busqueda: String? = null
    ): List<ProductoDto>

    @GET("productos/{id}")
    suspend fun obtener(
        @Path("id") id: Int
    ): ProductoDto

    @POST("productos")
    suspend fun crear(
        @Body producto: ProductoDto
    ): ProductoDto

    @PUT("productos/{id}")
    suspend fun actualizar(
        @Path("id") id: Int,
        @Body producto: ProductoDto
    ): ProductoDto

    @PATCH("productos/{id}/stock")
    suspend fun ajustarStock(
        @Path("id") id: Int,
        @Body ajuste: AjusteStockDto
    ): ProductoDto

    @DELETE("productos/{id}")
    suspend fun eliminar(
        @Path("id") id: Int
    ): Response<Unit>

    @GET("categorias")
    suspend fun categorias(): List<CategoriaDto>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    // Registrar una compra desde el carrito
    @POST("pedidos")
    suspend fun registrarPedido(
        @Body request: CrearPedidoRequest
    ): PedidoResponse

    // Consultar pedidos (solo administrador)
    @GET("admin/pedidos")
    suspend fun listarPedidosAdmin(): List<PedidoAdminDto>

    // Consultar detalles de un pedido
    @GET("admin/pedidos/{id}/detalles")
    suspend fun obtenerDetallesPedido(
        @Path("id") id: Int
    ): List<DetallePedidoDto>

    // Actualizar estado de un pedido
    @PATCH("admin/pedidos/{id}/estado")
    suspend fun actualizarEstadoPedido(
        @Path("id") id: Int,
        @Body request: ActualizarEstadoPedidoDto
    ): ActualizarEstadoPedidoResponse
}