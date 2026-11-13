
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

// Modelo que representa los productos del inventario
data class AdminProduct(
    val id: Int,
    val name: String,
    val price: String,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrudScreen() {

    // Lista temporal de productos
    val productList = remember {
        mutableStateListOf(
            AdminProduct(1, "Asus ROG Laptop", "1499.99", "Laptops"),
            AdminProduct(2, "iPhone 15 Pro", "999.99", "Celulares")
        )
    }

    val categoriasOpciones = listOf(
        "Laptops", "Celulares", "Gaming", "Audio", "Gadgets"
    )

    // Variables del formulario
    var prodName by remember { mutableStateOf("") }
    var prodPrice by remember { mutableStateOf("") }
    var prodCategory by remember { mutableStateOf(categoriasOpciones[0]) }
    var expandedDropdown by remember { mutableStateOf(false) }
    var editingProductId by remember { mutableStateOf<Int?>(null) }
    var nextId by remember { mutableStateOf(3) }

    val isFormValid = prodName.isNotBlank() && prodPrice.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121E2B))
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
    ) {

        // Título del formulario
        Text(
            text = if (editingProductId == null)
                "Registrar Nuevo Producto"
            else
                "Editar Producto Seleccionado",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = prodName,
            onValueChange = { prodName = it },
            label = { Text("Nombre del Hardware", color = Color.LightGray) },
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Selector de categorías
        ExposedDropdownMenuBox(
            expanded = expandedDropdown,
            onExpandedChange = { expandedDropdown = !expandedDropdown },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = prodCategory,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoría", color = Color.LightGray) },
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
                onDismissRequest = { expandedDropdown = false },
                modifier = Modifier.background(Color(0xFF1E293B))
            ) {
                categoriasOpciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(text = opcion, color = Color.White) },
                        onClick = {
                            prodCategory = opcion
                            expandedDropdown = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = prodPrice,
            onValueChange = { prodPrice = it },
            label = { Text("Precio de Venta ($)", color = Color.LightGray) },
            textStyle = TextStyle(color = Color.White),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones para guardar o cancelar la edición
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    if (editingProductId == null) {
                        productList.add(
                            AdminProduct(
                                id = nextId,
                                name = prodName,
                                price = prodPrice,
                                category = prodCategory
                            )
                        )
                        nextId++
                    } else {
                        val index = productList.indexOfFirst {
                            it.id == editingProductId
                        }

                        if (index != -1) {
                            productList[index] = AdminProduct(
                                id = editingProductId!!,
                                name = prodName,
                                price = prodPrice,
                                category = prodCategory
                            )
                        }

                        editingProductId = null
                    }

                    prodName = ""
                    prodPrice = ""
                    prodCategory = categoriasOpciones[0]
                },
                enabled = isFormValid,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (editingProductId == null)
                        Color(0xFF2563EB)
                    else
                        Color(0xFF10B981)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = if (editingProductId == null)
                        "GUARDAR PRODUCTO"
                    else
                        "ACTUALIZAR",
                    color = Color.White
                )
            }

            if (editingProductId != null) {
                Button(
                    onClick = {
                        editingProductId = null
                        prodName = ""
                        prodPrice = ""
                        prodCategory = categoriasOpciones[0]
                    },
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

        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider(
            color = Color(0xFF1E293B),
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Inventario Registrado (${productList.size})",
            style = MaterialTheme.typography.titleMedium,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Inventario con desplazamiento
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(productList, key = { it.id }) { product ->

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
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                product.name,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Text(
                                text = "Categoría: ${product.category}",
                                color = Color.LightGray,
                                style = MaterialTheme.typography.bodySmall
                            )

                            Text(
                                text = "$${product.price}",
                                color = Color(0xFF10B981),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        // Botón para editar
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = Color(0xFF2563EB),
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clickable {
                                    editingProductId = product.id
                                    prodName = product.name
                                    prodPrice = product.price
                                    prodCategory = product.category
                                }
                        )

                        // Botón para eliminar
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = Color(0xFFEF4444),
                            modifier = Modifier.clickable {
                                productList.remove(product)

                                if (editingProductId == product.id) {
                                    editingProductId = null
                                    prodName = ""
                                    prodPrice = ""
                                    prodCategory = categoriasOpciones[0]
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
