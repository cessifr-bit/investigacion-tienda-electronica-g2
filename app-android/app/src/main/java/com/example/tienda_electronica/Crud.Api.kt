package com.example.tienda_electronica

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tienda_electronica.data.CategoriaDto
import com.example.tienda_electronica.data.ProductoDto
import com.example.tienda_electronica.data.TiendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrudApi() {

    val vm: TiendaViewModel = viewModel()

    var seccionSeleccionada by remember { mutableStateOf(0) }

    val productos = vm.productos
    val categorias: List<CategoriaDto> = vm.categorias
    val cargando = vm.cargando
    val error = vm.error

    val onGuardar: (ProductoDto) -> Unit = { vm.guardar(it) }
    val onEliminar: (Int) -> Unit = { vm.eliminar(it) }

    // Variables del formulario
    var prodSku by remember { mutableStateOf("") }
    var prodName by remember { mutableStateOf("") }
    var prodPrice by remember { mutableStateOf("") }
    var prodStock by remember { mutableStateOf("") }
    var prodCategory by remember { mutableStateOf<CategoriaDto?>(null) }
    var expandedDropdown by remember { mutableStateOf(false) }
    var editingProduct by remember { mutableStateOf<ProductoDto?>(null) }

    val precio = prodPrice.toDoubleOrNull()
    val stock = prodStock.toIntOrNull()

    val isFormValid = prodSku.isNotBlank() &&
            prodName.isNotBlank() &&
            precio != null && precio >= 0 &&
            stock != null && stock >= 0

    fun limpiarFormulario() {
        editingProduct = null
        prodSku = ""
        prodName = ""
        prodPrice = ""
        prodStock = ""
        prodCategory = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121E2B))
    ) {

        // ---------- Pestañas ----------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FilterChip(
                selected = seccionSeleccionada == 0,
                onClick = { seccionSeleccionada = 0 },
                label = { Text("Productos") }
            )

            FilterChip(
                selected = seccionSeleccionada == 1,
                onClick = { seccionSeleccionada = 1 },
                label = { Text("Pedidos") }
            )
        }

        // ---------- SECCIÓN PRODUCTOS ----------
        if (seccionSeleccionada == 0) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(
                    top = 8.dp,
                    bottom = 20.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // ---------- Formulario ----------
                item {
                    Column {
                        Text(
                            text = if (editingProduct == null)
                                "Registrar Nuevo Producto"
                            else
                                "Editar Producto Seleccionado",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = prodSku,
                            onValueChange = { prodSku = it },
                            label = {
                                Text(
                                    "Código SKU (ej. LAP-ASU-001)",
                                    color = Color.LightGray
                                )
                            },
                            textStyle = TextStyle(color = Color.White),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = prodName,
                            onValueChange = { prodName = it },
                            label = {
                                Text(
                                    "Nombre del Hardware",
                                    color = Color.LightGray
                                )
                            },
                            textStyle = TextStyle(color = Color.White),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Selector de categorías
                        ExposedDropdownMenuBox(
                            expanded = expandedDropdown,
                            onExpandedChange = {
                                expandedDropdown = !expandedDropdown
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = prodCategory?.nombre
                                    ?: "Seleccionar categoría",
                                onValueChange = {},
                                readOnly = true,
                                label = {
                                    Text(
                                        "Categoría",
                                        color = Color.LightGray
                                    )
                                },
                                textStyle = TextStyle(color = Color.White),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expandedDropdown
                                    )
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            )

                            ExposedDropdownMenu(
                                expanded = expandedDropdown,
                                onDismissRequest = {
                                    expandedDropdown = false
                                },
                                modifier = Modifier.background(
                                    Color(0xFF1E293B)
                                )
                            ) {
                                categorias.forEach { opcion ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = opcion.nombre,
                                                color = Color.White
                                            )
                                        },
                                        onClick = {
                                            prodCategory = opcion
                                            expandedDropdown = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedTextField(
                                value = prodPrice,
                                onValueChange = { prodPrice = it },
                                label = {
                                    Text("Precio ($)", color = Color.LightGray)
                                },
                                textStyle = TextStyle(color = Color.White),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Decimal
                                ),
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp)
                            )

                            OutlinedTextField(
                                value = prodStock,
                                onValueChange = { prodStock = it },
                                label = {
                                    Text("Stock", color = Color.LightGray)
                                },
                                textStyle = TextStyle(color = Color.White),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Botones guardar y cancelar
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = {
                                    onGuardar(
                                        ProductoDto(
                                            idProducto = editingProduct?.idProducto ?: 0,
                                            codigoSku = prodSku.trim(),
                                            nombre = prodName.trim(),
                                            descripcion = editingProduct?.descripcion,
                                            precio = precio!!,
                                            stock = stock!!,
                                            imagenUrl = editingProduct?.imagenUrl,
                                            idCategoria = prodCategory?.idCategoria,
                                            idMarca = editingProduct?.idMarca
                                        )
                                    )
                                    limpiarFormulario()
                                },
                                enabled = isFormValid,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (editingProduct == null)
                                        Color(0xFF2563EB)
                                    else
                                        Color(0xFF10B981)
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = if (editingProduct == null)
                                        "GUARDAR PRODUCTO"
                                    else
                                        "ACTUALIZAR",
                                    color = Color.White
                                )
                            }

                            if (editingProduct != null) {
                                Button(
                                    onClick = { limpiarFormulario() },
                                    modifier = Modifier.height(48.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Gray
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text("CANCELAR", color = Color.White)
                                }
                            }
                        }

                        if (error != null) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = error,
                                color = Color(0xFFEF4444)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        HorizontalDivider(
                            color = Color(0xFF1E293B),
                            thickness = 2.dp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Inventario Registrado (${productos.size})",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.LightGray
                        )

                        if (cargando) {
                            Spacer(modifier = Modifier.height(8.dp))
                            LinearProgressIndicator(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                // ---------- Inventario ----------
                items(productos, key = { it.idProducto }) { product ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E293B)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(14.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    product.nombre,
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Text(
                                    text = "SKU: ${product.codigoSku} · Stock: ${product.stock}",
                                    color = Color.LightGray,
                                    style = MaterialTheme.typography.bodySmall
                                )

                                Text(
                                    text = "Categoría: ${product.nombreCategoria ?: "Sin categoría"}",
                                    color = Color.LightGray,
                                    style = MaterialTheme.typography.bodySmall
                                )

                                Text(
                                    text = "$" + "%.2f".format(product.precio),
                                    color = Color(0xFF10B981),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            // Editar producto
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = Color(0xFF2563EB),
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .clickable {
                                        editingProduct = product
                                        prodSku = product.codigoSku
                                        prodName = product.nombre
                                        prodPrice = product.precio.toString()
                                        prodStock = product.stock.toString()
                                        prodCategory = categorias.firstOrNull {
                                            it.idCategoria == product.idCategoria
                                        }
                                    }
                            )

                            // Eliminar producto
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                tint = Color(0xFFEF4444),
                                modifier = Modifier.clickable {
                                    onEliminar(product.idProducto)

                                    if (editingProduct?.idProducto == product.idProducto) {
                                        limpiarFormulario()
                                    }
                                }
                            )
                        }
                    }
                }
            }

        } else {
            // ---------- SECCIÓN PEDIDOS ----------
            PedidosAdminContent(vm)
        }
    }
}

// ======================================================
// ADMINISTRACIÓN DE PEDIDOS
// ======================================================

@Composable
fun PedidosAdminContent(vm: TiendaViewModel) {

    val pedidos = vm.pedidosAdmin
    val detalles = vm.detallesPedido

    LaunchedEffect(Unit) {
        vm.cargarPedidosAdmin()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121E2B))
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Pedidos registrados (${pedidos.size})",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )

                TextButton(
                    onClick = { vm.cargarPedidosAdmin() }
                ) {
                    Text("Actualizar")
                }
            }

            if (vm.cargandoPedidos) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            vm.mensajeAdmin?.let { mensaje ->
                Text(
                    text = mensaje,
                    color = if (mensaje.contains("correctamente", ignoreCase = true))
                        Color(0xFF10B981)
                    else
                        Color(0xFFEF4444)
                )
            }
        }

        if (pedidos.isEmpty() && !vm.cargandoPedidos) {
            item {
                Text(
                    "No hay pedidos registrados.",
                    color = Color.LightGray
                )
            }
        }

        items(pedidos, key = { it.idPedido }) { pedido ->

            var menuAbierto by remember(pedido.idPedido) {
                mutableStateOf(false)
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E293B)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        "Pedido #${pedido.idPedido}",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        "Cliente: ${pedido.nombreCliente}",
                        color = Color.LightGray
                    )

                    Text(
                        "Correo: ${pedido.emailCliente}",
                        color = Color.LightGray
                    )

                    Text(
                        "Fecha: ${pedido.fechaPedido}",
                        color = Color.LightGray
                    )

                    Text(
                        "Total: $${"%.2f".format(pedido.total)}",
                        color = Color(0xFF10B981)
                    )

                    Text(
                        "Estado: ${pedido.estado}",
                        color = Color.White
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                vm.cargarDetallesPedido(pedido.idPedido)
                            }
                        ) {
                            Text("Ver detalles")
                        }

                        Box {
                            Button(
                                onClick = { menuAbierto = true }
                            ) {
                                Text("Cambiar estado")
                            }

                            DropdownMenu(
                                expanded = menuAbierto,
                                onDismissRequest = {
                                    menuAbierto = false
                                }
                            ) {
                                listOf(
                                    "PENDIENTE",
                                    "PAGADO",
                                    "ENVIADO",
                                    "ENTREGADO",
                                    "CANCELADO"
                                ).forEach { estado ->

                                    DropdownMenuItem(
                                        text = { Text(estado) },
                                        onClick = {
                                            menuAbierto = false
                                            vm.actualizarEstadoPedido(
                                                pedido.idPedido,
                                                estado
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Detalles del pedido seleccionado
                    if (vm.pedidoSeleccionado == pedido.idPedido) {

                        HorizontalDivider(color = Color.Gray)

                        Text(
                            "Detalles del pedido",
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall
                        )

                        if (detalles.isEmpty()) {
                            Text(
                                "No hay detalles disponibles.",
                                color = Color.LightGray
                            )
                        }

                        detalles.forEach { detalle ->
                            Column {
                                Text(
                                    detalle.nombreProducto,
                                    color = Color.White
                                )

                                Text(
                                    "Cantidad: ${detalle.cantidad}",
                                    color = Color.LightGray
                                )

                                Text(
                                    "Precio: $${"%.2f".format(detalle.precioUnitario)}",
                                    color = Color.LightGray
                                )

                                Text(
                                    "Subtotal: $${"%.2f".format(detalle.subtotal)}",
                                    color = Color(0xFF10B981)
                                )
                            }

                            HorizontalDivider(color = Color.DarkGray)
                        }
                    }
                }
            }
        }
    }
}
