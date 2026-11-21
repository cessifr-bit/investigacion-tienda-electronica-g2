package com.example.tienda_electronica

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tienda_electronica.data.TiendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Catalogo(
    initialCategory: String = "Todos",
    initialQuery: String = ""
) {

    val context = LocalContext.current
    val vm: TiendaViewModel = viewModel()
    val allProducts = vm.productos

    var searchQuery by remember(initialQuery) {
        mutableStateOf(initialQuery)
    }

    var selectedCategory by remember(initialCategory) {
        mutableStateOf(initialCategory)
    }

    val categories = listOf("Todos") +
            vm.categorias.map { it.nombre }

    // Filtrar productos por búsqueda y categoría.
    val filteredProducts = allProducts.filter { product ->

        val query = searchQuery.trim()

        val matchesSearch = query.isBlank() || listOfNotNull(
            product.nombre,
            product.codigoSku,
            product.descripcion,
            product.nombreMarca,
            product.nombreCategoria
        ).any { value ->
            value.contains(query, ignoreCase = true)
        }

        val matchesCategory =
            selectedCategory == "Todos" ||
                    product.nombreCategoria == selectedCategory

        matchesSearch && matchesCategory
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
    ) {

        // Campo de búsqueda.
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    "Buscar productos, componentes...",
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color(0xFF2563EB)
                )
            },
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color(0xFF2563EB),
                focusedBorderColor = Color(0xFF2563EB),
                unfocusedBorderColor = Color(0xFF1E293B)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Filtros por categoría.
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { category ->

                val isSelected = category == selectedCategory

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (isSelected) {
                                Color(0xFF2563EB)
                            } else {
                                Color(0xFF1E293B)
                            }
                        )
                        .clickable {
                            selectedCategory = category
                        }
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                ) {
                    Text(
                        text = category,
                        color = if (isSelected) {
                            Color.White
                        } else {
                            Color.LightGray
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cuadrícula de productos.
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            // Mensaje cuando no existen resultados.
            if (filteredProducts.isEmpty()) {

                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 65.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color(0xFF3B82F6),
                            modifier = Modifier.size(55.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = when {
                                searchQuery.isNotBlank() ->
                                    "No se encontraron productos"

                                selectedCategory != "Todos" ->
                                    "No hay productos en esta categoría"

                                else ->
                                    "No hay productos disponibles"
                            },
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = when {
                                searchQuery.isNotBlank() ->
                                    "Prueba con otro nombre o palabra clave."

                                selectedCategory != "Todos" ->
                                    "Actualmente no tenemos productos de esta categoría."

                                else ->
                                    "No hay productos para mostrar en este momento."
                            },
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                searchQuery = ""
                                selectedCategory = "Todos"
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2563EB)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Ver todos los productos")
                        }
                    }
                }

            } else {

                // Tarjetas de productos.
                items(
                    filteredProducts,
                    key = { it.idProducto }
                ) { product ->

                    // Seleccionar imagen local para productos problemáticos.
                    val imagenProducto: Any = when {
                        product.codigoSku == "COM-AMD-004" ->
                            R.drawable.amd_ryzen_7800x3d

                        product.nombre.contains(
                            "Monitor ASUS TUF",
                            ignoreCase = true
                        ) ->
                            R.drawable.monitor_asus_tuf_27

                        else ->
                            product.imagenUrl ?: ""
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E293B)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {

                            // Imagen del producto.
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0xFF0F172A),
                                                Color(0xFF1E293B)
                                            )
                                        ),
                                        RoundedCornerShape(12.dp)
                                    )
                            ) {

                                AsyncImage(
                                    model = imagenProducto,
                                    contentDescription = product.nombre,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    onError = { state ->
                                        Log.e(
                                            "ImagenProducto",
                                            "Error cargando ${product.nombre}: $imagenProducto",
                                            state.result.throwable
                                        )
                                    }
                                )

                                // Stock del producto.
                                Row(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(6.dp)
                                        .background(
                                            Color(0xAA000000),
                                            RoundedCornerShape(8.dp)
                                        )
                                        .padding(
                                            horizontal = 6.dp,
                                            vertical = 2.dp
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = null,
                                        tint = Color(0xFFFFB703),
                                        modifier = Modifier.size(12.dp)
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        "Stock: ${product.stock}",
                                        color = Color.White,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                product.nombre,
                                color = Color.White,
                                maxLines = 1,
                                style = MaterialTheme.typography.titleSmall
                            )

                            Text(
                                product.nombreCategoria ?: "Sin categoría",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodySmall
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "$${product.precio}",
                                    color = Color(0xFF10B981),
                                    style = MaterialTheme.typography.titleMedium
                                )

                                IconButton(
                                    onClick = {
                                        val agregado = vm.agregarAlCarrito(product)

                                        if (agregado) {
                                            Toast.makeText(
                                                context,
                                                "Agregado al carrito: ${product.nombre}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                vm.error ?: "No se pudo agregar el producto",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    },
                                    modifier = Modifier
                                        .size(38.dp)
                                        .background(
                                            if (product.stock > 0) {
                                                Color(0xFF2563EB)
                                            } else {
                                                Color.Gray
                                            },
                                            CircleShape
                                        )
                                ) {
                                    Icon(
                                        Icons.Default.Add,
                                        contentDescription = "Agregar al carrito",
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
