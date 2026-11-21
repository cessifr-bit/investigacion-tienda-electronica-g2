package com.example.tienda_electronica.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tienda_electronica.data.TiendaViewModel
import com.example.tienda_electronica.ui.theme.*

@Composable
fun CarritoScreen(
    onContinueShopping: () -> Unit = {}
) {
    val vm: TiendaViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulNoche)
            .padding(18.dp)
    ) {

        Text(
            text = "Mi carrito",
            color = Blanco,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Mensaje de compra exitosa
        vm.pedidoCreado?.let { pedido ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AzulSuperficie
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "¡Compra realizada correctamente!",
                        color = Blanco,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Pedido #${pedido.idPedido}",
                        color = AzulBrillante
                    )

                    Text(
                        "Total: $${"%.2f".format(pedido.total)}",
                        color = Blanco,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Estado: ${pedido.estado}",
                        color = GrisAzulado
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onContinueShopping,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AzulBrillante
                        )
                    ) {
                        Text("Seguir comprando", color = Blanco)
                    }
                }
            }
        }

        // Mensajes de error
        vm.error?.let { mensaje ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mensaje,
                color = Color(0xFFFF6B6B),
                fontSize = 14.sp
            )
        }

        if (vm.carrito.isEmpty()) {

            if (vm.pedidoCreado == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Tu carrito está vacío",
                            color = Blanco,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = onContinueShopping,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AzulBrillante
                            )
                        ) {
                            Text("Explorar productos", color = Blanco)
                        }
                    }
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    vm.carrito,
                    key = { it.producto.idProducto }
                ) { item ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = AzulSuperficie
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AsyncImage(
                                model = item.producto.imagenUrl,
                                contentDescription = item.producto.nombre,
                                modifier = Modifier
                                    .size(76.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(AzulNoche)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    item.producto.nombre,
                                    color = Blanco,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 2
                                )

                                Text(
                                    "$${"%.2f".format(item.producto.precio)}",
                                    color = AzulBrillante,
                                    fontWeight = FontWeight.Bold
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Button(
                                        onClick = {
                                            vm.cambiarCantidad(
                                                item.producto.idProducto,
                                                item.cantidad - 1
                                            )
                                        },
                                        enabled = !vm.procesandoCompra,
                                        contentPadding = PaddingValues(
                                            horizontal = 12.dp,
                                            vertical = 0.dp
                                        ),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = AzulBrillante
                                        )
                                    ) {
                                        Text("−", color = Blanco)
                                    }

                                    Text(
                                        "${item.cantidad}",
                                        color = Blanco
                                    )

                                    Button(
                                        onClick = {
                                            vm.cambiarCantidad(
                                                item.producto.idProducto,
                                                item.cantidad + 1
                                            )
                                        },
                                        enabled = !vm.procesandoCompra &&
                                                item.cantidad < item.producto.stock,
                                        contentPadding = PaddingValues(
                                            horizontal = 12.dp,
                                            vertical = 0.dp
                                        ),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = AzulBrillante
                                        )
                                    ) {
                                        Text("+", color = Blanco)
                                    }

                                    TextButtonEliminar(
                                        onClick = {
                                            vm.quitarDelCarrito(
                                                item.producto.idProducto
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Unidades: ${vm.cantidadEnCarrito()}",
                color = GrisAzulado
            )

            Text(
                "Total: $${"%.2f".format(vm.totalCarrito())}",
                color = Blanco,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Botón para registrar la compra
            Button(
                onClick = {
                    vm.finalizarCompra()
                },
                enabled = !vm.procesandoCompra && vm.carrito.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulBrillante
                )
            ) {
                if (vm.procesandoCompra) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = Blanco,
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Procesando compra...", color = Blanco)
                } else {
                    Text(
                        "Finalizar compra",
                        color = Blanco,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    vm.vaciarCarrito()
                },
                enabled = !vm.procesandoCompra,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9B2635)
                )
            ) {
                Text("Vaciar carrito", color = Blanco)
            }
        }
    }
}

@Composable
private fun TextButtonEliminar(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(
            horizontal = 8.dp,
            vertical = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF9B2635)
        )
    ) {
        Text(
            "Quitar",
            color = Blanco,
            fontSize = 11.sp
        )
    }
}
