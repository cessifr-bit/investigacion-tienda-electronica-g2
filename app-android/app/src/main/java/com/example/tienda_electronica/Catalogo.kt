package com.example.tienda_electronica

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
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
import androidx.compose.ui.unit.dp

data class TechProduct(
    val name: String,
    val category: String,
    val price: Double,
    val rating: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Catalogo() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Todos") }

    val categories = listOf("Todos", "Laptops", "Gaming", "Gadgets", "Audio")
    val allProducts = listOf(
        TechProduct("Asus ROG Laptop Pro", "Laptops", 1499.99, 4.9),
        TechProduct("Quantum Phone X1", "Gadgets", 899.99, 4.7),
        TechProduct("Sony WH-1000XM5", "Audio", 349.95, 4.8),
        TechProduct("Logitech G Pro Keyboard", "Gaming", 149.00, 4.6),
        TechProduct("Razer Viper Mouse", "Gaming", 79.99, 4.5),
        TechProduct("MacBook Pro M3", "Laptops", 1999.99, 5.0)
    )

    val filteredProducts = allProducts.filter { product ->
        val matchesSearch = product.name.contains(searchQuery, ignoreCase = true)
        val matchesCategory = selectedCategory == "Todos" || product.category == selectedCategory
        matchesSearch && matchesCategory
    }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0F172A)).padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search hardware, components...", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar", tint = Color(0xFF2563EB)) },
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color(0xFF2563EB),
                focusedBorderColor = Color(0xFF2563EB),
                unfocusedBorderColor = Color(0xFF1E293B)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { category ->
                val isSelected = category == selectedCategory
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) Color(0xFF2563EB) else Color(0xFF1E293B))
                        .clickable { selectedCategory = category }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = category,
                        color = if (isSelected) Color.White else Color.LightGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredProducts) { product ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(110.dp)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color(0xFF0F172A), Color(0xFF1E293B))
                                    ),
                                    RoundedCornerShape(12.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(6.dp)
                                    .background(Color(0xAA000000), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFB703), modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(product.rating.toString(), color = Color.White, style = MaterialTheme.typography.labelSmall)
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(product.name, color = Color.White, maxLines = 1, style = MaterialTheme.typography.titleSmall)
                        Text(product.category, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("$${product.price}", color = Color(0xFF10B981), style = MaterialTheme.typography.titleMedium)
                            IconButton(
                                onClick = { },
                                modifier = Modifier.size(28.dp).background(Color(0xFF2563EB), CircleShape)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}