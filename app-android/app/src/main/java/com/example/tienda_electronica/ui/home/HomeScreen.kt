
package com.example.tienda_electronica.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tienda_electronica.ui.theme.AzulNoche
import com.example.tienda_electronica.ui.theme.AzulSuperficie
import com.example.tienda_electronica.ui.theme.AzulTarjeta
import com.example.tienda_electronica.ui.theme.AzulElectrico
import com.example.tienda_electronica.ui.theme.AzulBrillante
import com.example.tienda_electronica.ui.theme.AzulCyan
import com.example.tienda_electronica.ui.theme.Blanco
import com.example.tienda_electronica.ui.theme.GrisAzulado
import kotlinx.coroutines.delay

// Modelo para la información de los banners
data class BannerItem(
    val label: String,
    val title: String,
    val description: String,
    val image: String
)

// Modelo para las categorías
data class CategoryItem(
    val name: String,
    val image: String
)

// Modelo para los productos
data class ProductItem(
    val name: String,
    val description: String,
    val image: String,
    val price: String,
    val oldPrice: String,
    val rating: String,
    val ratingCount: String,
    val discount: String? = null
)

// Imágenes de demostración para el carrusel
private val banners = listOf(
    BannerItem(
        label = "NUEVA GENERACIÓN",
        title = "Samsung Galaxy S25",
        description = "Más potencia. Más allá.",
        image = "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=1000&auto=format&fit=crop&q=85"
    ),
    BannerItem(
        label = "SONIDO PREMIUM",
        title = "Sonido sin límites",
        description = "Disfruta cada detalle.",
        image = "https://images.unsplash.com/photo-1546435770-a3e426bf472b?w=1000&auto=format&fit=crop&q=85"
    ),
    BannerItem(
        label = "POTENCIA Y RENDIMIENTO",
        title = "Tu próxima laptop",
        description = "Encuentra el equipo ideal para ti.",
        image = "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=1000&auto=format&fit=crop&q=85"
    ),
    BannerItem(
        label = "TECNOLOGÍA INTELIGENTE",
        title = "Tecnología para tu día",
        description = "Descubre nuevos dispositivos.",
        image = "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=1000&auto=format&fit=crop&q=85"
    )
)

// Categorías con imágenes de productos tecnológicos
private val categories = listOf(
    CategoryItem(
        "Celulares",
        "https://images.unsplash.com/photo-1592899677977-9c10ca588bbd?w=500&auto=format&fit=crop&q=85"
    ),
    CategoryItem(
        "Audífonos",
        "https://images.unsplash.com/photo-1546435770-a3e426bf472b?w=500&auto=format&fit=crop&q=85"
    ),
    CategoryItem(
        "Laptops",
        "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=500&auto=format&fit=crop&q=85"
    ),
    CategoryItem(
        "Smartwatch",
        "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=500&auto=format&fit=crop&q=85"
    ),
    CategoryItem(
        "Gaming",
        "https://images.unsplash.com/photo-1486572788966-cfd3df1f5b42?w=500&auto=format&fit=crop&q=85"
    )
)

// Productos y precios de demostración.
// Se podrán reemplazar cuando se conecte el backend.
private val products = listOf(
    ProductItem(
        name = "Audífonos inalámbricos",
        description = "Sonido de alta calidad",
        image = "https://images.unsplash.com/photo-1606220945770-b5b6c2c55bf1?w=800&auto=format&fit=crop&q=85",
        price = "$249.99",
        oldPrice = "$309.99",
        rating = "4.8",
        ratingCount = "12k",
        discount = "-20%"
    ),
    ProductItem(
        name = "Consola PlayStation",
        description = "Entretenimiento y gaming",
        image = "https://images.unsplash.com/photo-1606813907291-d86efa9b94db?w=800&auto=format&fit=crop&q=85",
        price = "$449.99",
        oldPrice = "$529.99",
        rating = "4.7",
        ratingCount = "8k",
        discount = "-15%"
    ),
    ProductItem(
        name = "Laptop profesional",
        description = "Rendimiento y velocidad",
        image = "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=800&auto=format&fit=crop&q=85",
        price = "$799.00",
        oldPrice = "$899.00",
        rating = "4.9",
        ratingCount = "5k",
        discount = "-11%"
    ),
    ProductItem(
        name = "Smartwatch deportivo",
        description = "Tecnología para tu día",
        image = "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=800&auto=format&fit=crop&q=85",
        price = "$89.00",
        oldPrice = "$109.00",
        rating = "4.7",
        ratingCount = "3k",
        discount = "NUEVO"
    )
)

// Pantalla principal de la tienda
@Composable
fun HomeScreen(
    onCatalogClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onManagementClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    showBottomNavigation: Boolean = true
) {
    var searchText by remember { mutableStateOf("") }
    var selectedNavigation by remember { mutableStateOf(0) }

    // Filtra los productos según lo que escriba el usuario
    val filteredProducts = products.filter {
        it.name.contains(searchText, ignoreCase = true) ||
                it.description.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        containerColor = AzulNoche,
        bottomBar = {
            // MainActivity controla la navegación principal
            if (showBottomNavigation) {
                HomeBottomNavigation(
                    selectedItem = selectedNavigation,
                    onItemSelected = { selected ->
                        selectedNavigation = selected

                        when (selected) {
                            0 -> {}
                            1 -> onCatalogClick()
                            2 -> onManagementClick()
                            3 -> onProfileClick()
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AzulNoche)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 20.dp)
        ) {

            HomeHeader(
                onCartClick = onCartClick
            )

            SearchBar(
                searchText = searchText,
                onSearchChange = { searchText = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            PromotionCarousel(
                onExploreClick = onCatalogClick
            )

            Spacer(modifier = Modifier.height(28.dp))

            SectionHeader(
                title = "Categorías destacadas",
                action = "Ver todas",
                onActionClick = onCatalogClick,
                showFlame = true
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                categories.forEach { category ->
                    CategoryCard(category = category)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            SectionHeader(
                title = "Productos destacados",
                action = "Ver todos",
                onActionClick = onCatalogClick,
                showFlame = true
            )

            Spacer(modifier = Modifier.height(15.dp))

            if (filteredProducts.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                        .height(140.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(AzulSuperficie),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron productos",
                        color = GrisAzulado,
                        fontSize = 14.sp
                    )
                }
            } else {
                // Los productos se muestran en dos columnas
                Column(
                    modifier = Modifier.padding(horizontal = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    filteredProducts.chunked(2).forEach { rowProducts ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            rowProducts.forEach { product ->
                                ProductCard(
                                    product = product,
                                    onAddClick = onCartClick,
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            if (rowProducts.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

// Encabezado de la tienda
@Composable
private fun HomeHeader(
    onCartClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StoreLogo()

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "TIENDA",
                color = Blanco,
                fontSize = 21.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.5.sp
            )

            Text(
                text = "ELECTRÓNICA",
                color = AzulBrillante,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )

            Text(
                text = "Tecnología para ti",
                color = GrisAzulado,
                fontSize = 12.sp
            )
        }

        // Acceso al carrito
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .border(
                    width = 1.dp,
                    color = AzulElectrico.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(30.dp)
                )
                .background(AzulSuperficie.copy(alpha = 0.75f))
                .clickable { onCartClick() }
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                CartIcon(
                    modifier = Modifier.size(26.dp),
                    color = AzulBrillante
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(start = 15.dp, bottom = 15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(17.dp)
                            .background(Color(0xFFEF4444), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "2",
                            color = Blanco,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Carrito",
                color = Blanco,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// Logo de la tienda dibujado con Canvas
@Composable
private fun StoreLogo() {
    Box(
        modifier = Modifier.size(65.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val blue = AzulBrillante
            val stroke = 3.dp.toPx()

            val bag = Path().apply {
                moveTo(size.width * 0.23f, size.height * 0.30f)
                quadraticTo(
                    size.width * 0.12f,
                    size.height * 0.30f,
                    size.width * 0.12f,
                    size.height * 0.43f
                )
                lineTo(size.width * 0.12f, size.height * 0.83f)
                quadraticTo(
                    size.width * 0.12f,
                    size.height * 0.92f,
                    size.width * 0.23f,
                    size.height * 0.92f
                )
                lineTo(size.width * 0.78f, size.height * 0.92f)
                quadraticTo(
                    size.width * 0.89f,
                    size.height * 0.92f,
                    size.width * 0.89f,
                    size.height * 0.83f
                )
                lineTo(size.width * 0.89f, size.height * 0.43f)
                quadraticTo(
                    size.width * 0.89f,
                    size.height * 0.30f,
                    size.width * 0.77f,
                    size.height * 0.30f
                )
                close()
            }

            drawPath(
                path = bag,
                color = blue,
                style = Stroke(width = stroke, cap = StrokeCap.Round)
            )

            val handle = Path().apply {
                moveTo(size.width * 0.30f, size.height * 0.31f)
                quadraticTo(
                    size.width * 0.30f,
                    size.height * 0.04f,
                    size.width * 0.50f,
                    size.height * 0.04f
                )
                quadraticTo(
                    size.width * 0.70f,
                    size.height * 0.04f,
                    size.width * 0.70f,
                    size.height * 0.31f
                )
            }

            drawPath(
                path = handle,
                color = blue,
                style = Stroke(width = stroke, cap = StrokeCap.Round)
            )

            val lightning = Path().apply {
                moveTo(size.width * 0.57f, size.height * 0.35f)
                lineTo(size.width * 0.37f, size.height * 0.59f)
                lineTo(size.width * 0.49f, size.height * 0.59f)
                lineTo(size.width * 0.42f, size.height * 0.79f)
                lineTo(size.width * 0.67f, size.height * 0.51f)
                lineTo(size.width * 0.54f, size.height * 0.51f)
                close()
            }

            drawPath(
                path = lightning,
                color = AzulCyan
            )
        }
    }
}

// Buscador de productos
@Composable
private fun SearchBar(
    searchText: String,
    onSearchChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
            .height(58.dp),
        placeholder = {
            Text(
                text = "Buscar productos...",
                color = GrisAzulado,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            SearchIcon(
                modifier = Modifier.size(23.dp),
                color = GrisAzulado
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(30.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Blanco,
            unfocusedTextColor = Blanco,
            focusedContainerColor = AzulSuperficie.copy(alpha = 0.8f),
            unfocusedContainerColor = AzulSuperficie.copy(alpha = 0.8f),
            focusedBorderColor = AzulElectrico,
            unfocusedBorderColor = AzulElectrico.copy(alpha = 0.35f),
            cursorColor = AzulCyan
        )
    )
}

// Carrusel de promociones con iluminación azul suave
@Composable
private fun PromotionCarousel(
    onExploreClick: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { banners.size }
    )

    // Cambia de promoción automáticamente cada 4.5 segundos
    LaunchedEffect(Unit) {
        while (true) {
            delay(4500)
            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(245.dp),
            contentPadding = PaddingValues(horizontal = 18.dp),
            pageSpacing = 12.dp
        ) { page ->

            val banner = banners[page]

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(23.dp))
                    .border(
                        width = 1.dp,
                        color = AzulBrillante.copy(alpha = 0.45f),
                        shape = RoundedCornerShape(23.dp)
                    )
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF07152F),
                                Color(0xFF06265C),
                                Color(0xFF020B1D)
                            )
                        )
                    )
            ) {

                // Fotografía del producto a la derecha
                AsyncImage(
                    model = banner.image,
                    contentDescription = banner.title,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxWidth(0.62f)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Degradado para integrar la imagen con el fondo
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF06132C),
                                    Color(0xFF06132C).copy(alpha = 0.98f),
                                    Color(0xFF06132C).copy(alpha = 0.76f),
                                    Color(0xFF06132C).copy(alpha = 0.12f),
                                    Color.Transparent
                                ),
                                startX = 0f,
                                endX = 900f
                            )
                        )
                )

                // Brillo azul suave en el fondo, sin líneas superpuestas
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    AzulElectrico.copy(alpha = 0.20f),
                                    AzulElectrico.copy(alpha = 0.07f),
                                    Color.Transparent
                                ),
                                center = Offset(
                                    x = 0.82f * 900f,
                                    y = 0.12f * 245f
                                ),
                                radius = 360f
                            )
                        )
                )

                // Contenido de la promoción
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(0.65f)
                        .padding(start = 19.dp, top = 15.dp, bottom = 15.dp)
                ) {
                    Text(
                        text = banner.label,
                        modifier = Modifier
                            .clip(RoundedCornerShape(7.dp))
                            .background(AzulElectrico.copy(alpha = 0.90f))
                            .padding(horizontal = 9.dp, vertical = 5.dp),
                        color = Blanco,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = banner.title,
                        color = Blanco,
                        fontSize = 23.sp,
                        lineHeight = 27.sp,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = banner.description,
                        color = GrisAzulado,
                        fontSize = 12.sp,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = onExploreClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AzulBrillante,
                            contentColor = Blanco
                        ),
                        shape = RoundedCornerShape(25.dp),
                        contentPadding = PaddingValues(
                            horizontal = 17.dp,
                            vertical = 8.dp
                        )
                    ) {
                        Text(
                            text = "Comprar ahora  →",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Indicadores de posición del carrusel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(banners.size) { index ->
                val selected = pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .width(if (selected) 22.dp else 7.dp)
                        .height(7.dp)
                        .clip(CircleShape)
                        .background(
                            if (selected) AzulBrillante else AzulTarjeta
                        )
                )
            }
        }
    }
}

// Encabezados de las secciones
@Composable
private fun SectionHeader(
    title: String,
    action: String,
    onActionClick: () -> Unit,
    showFlame: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showFlame) {
            FlameIcon(
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))
        }

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Blanco,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$action  →",
            modifier = Modifier.clickable { onActionClick() },
            color = AzulBrillante,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Icono de fuego con degradado azul
@Composable
private fun FlameIcon(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val flame = Path().apply {
            moveTo(size.width * 0.52f, size.height * 0.02f)
            cubicTo(
                size.width * 0.58f, size.height * 0.25f,
                size.width * 0.91f, size.height * 0.37f,
                size.width * 0.82f, size.height * 0.69f
            )
            cubicTo(
                size.width * 0.77f, size.height * 0.94f,
                size.width * 0.59f, size.height,
                size.width * 0.43f, size.height * 0.96f
            )
            cubicTo(
                size.width * 0.12f, size.height * 0.90f,
                size.width * 0.12f, size.height * 0.60f,
                size.width * 0.27f, size.height * 0.42f
            )
            cubicTo(
                size.width * 0.28f, size.height * 0.62f,
                size.width * 0.42f, size.height * 0.62f,
                size.width * 0.45f, size.height * 0.48f
            )
            cubicTo(
                size.width * 0.48f, size.height * 0.32f,
                size.width * 0.42f, size.height * 0.19f,
                size.width * 0.52f, size.height * 0.02f
            )
            close()
        }

        drawPath(
            path = flame,
            brush = Brush.verticalGradient(
                colors = listOf(
                    AzulCyan,
                    AzulBrillante,
                    AzulElectrico
                )
            )
        )

        // Centro de la llama
        val innerFlame = Path().apply {
            moveTo(size.width * 0.51f, size.height * 0.55f)
            cubicTo(
                size.width * 0.66f, size.height * 0.68f,
                size.width * 0.65f, size.height * 0.85f,
                size.width * 0.49f, size.height * 0.91f
            )
            cubicTo(
                size.width * 0.34f, size.height * 0.86f,
                size.width * 0.35f, size.height * 0.72f,
                size.width * 0.51f, size.height * 0.55f
            )
            close()
        }

        drawPath(
            path = innerFlame,
            color = AzulNoche.copy(alpha = 0.65f)
        )
    }
}

// Tarjeta de categoría
@Composable
private fun CategoryCard(
    category: CategoryItem
) {
    Column(
        modifier = Modifier
            .width(112.dp)
            .clip(RoundedCornerShape(18.dp))
            .border(
                width = 1.dp,
                color = AzulBrillante.copy(alpha = 0.40f),
                shape = RoundedCornerShape(18.dp)
            )
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0C1C3B),
                        AzulSuperficie
                    )
                )
            )
            .padding(9.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = category.image,
            contentDescription = category.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .clip(RoundedCornerShape(13.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text = category.name,
            color = Blanco,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

// Tarjeta de producto
@Composable
private fun ProductCard(
    product: ProductItem,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember(product.name) {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .border(
                width = 1.dp,
                color = AzulBrillante.copy(alpha = 0.38f),
                shape = RoundedCornerShape(19.dp)
            ),
        shape = RoundedCornerShape(19.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF071936)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            // Fotografía y controles del producto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.05f)
                    .clip(RoundedCornerShape(13.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF10264A),
                                Color(0xFF07152D)
                            )
                        )
                    )
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    contentScale = ContentScale.Fit
                )

                if (product.discount != null) {
                    Text(
                        text = product.discount,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .clip(RoundedCornerShape(20.dp))
                            .background(AzulBrillante)
                            .padding(horizontal = 9.dp, vertical = 5.dp),
                        color = Blanco,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                // Botón de favoritos
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(AzulNoche.copy(alpha = 0.80f))
                        .clickable {
                            isFavorite = !isFavorite
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isFavorite) "♥" else "♡",
                        color = if (isFavorite) {
                            Color(0xFFEF4444)
                        } else {
                            Color(0xFFB8C8E8)
                        },
                        fontSize = 21.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = product.name,
                color = Blanco,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = product.description,
                color = GrisAzulado,
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Calificación de ejemplo, no proviene todavía del backend
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "★★★★★",
                    color = Color(0xFFFBBF24),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = product.rating,
                    color = Blanco,
                    fontSize = 10.sp
                )

                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    text = "(${product.ratingCount})",
                    color = GrisAzulado,
                    fontSize = 9.sp
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = product.price,
                color = AzulBrillante,
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = product.oldPrice,
                color = GrisAzulado,
                fontSize = 10.sp
            )

            Spacer(modifier = Modifier.height(9.dp))

            Button(
                onClick = onAddClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(39.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulBrillante,
                    contentColor = Blanco
                ),
                contentPadding = PaddingValues(horizontal = 5.dp)
            ) {
                CartIcon(
                    modifier = Modifier.size(17.dp),
                    color = Blanco
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Agregar",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// Barra de navegación alternativa.
// La navegación principal de la aplicación sigue en MainActivity.
@Composable
private fun HomeBottomNavigation(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        "Inicio",
        "Catálogo",
        "Gestión",
        "Perfil"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AzulSuperficie)
            .padding(horizontal = 8.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        onItemSelected(index)
                    }
                    .padding(vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationIcon(
                    index = index,
                    color = if (selectedItem == index) {
                        AzulBrillante
                    } else {
                        GrisAzulado
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item,
                    color = if (selectedItem == index) {
                        AzulBrillante
                    } else {
                        GrisAzulado
                    },
                    fontSize = 10.sp,
                    fontWeight = if (selectedItem == index) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    }
                )
            }
        }
    }
}

// Iconos de la navegación alternativa
@Composable
private fun NavigationIcon(
    index: Int,
    color: Color
) {
    Canvas(
        modifier = Modifier.size(22.dp)
    ) {
        val strokeWidth = 2.dp.toPx()

        when (index) {
            0 -> {
                val path = Path().apply {
                    moveTo(size.width * 0.12f, size.height * 0.48f)
                    lineTo(size.width * 0.5f, size.height * 0.12f)
                    lineTo(size.width * 0.88f, size.height * 0.48f)
                    lineTo(size.width * 0.82f, size.height * 0.48f)
                    lineTo(size.width * 0.82f, size.height * 0.9f)
                    lineTo(size.width * 0.58f, size.height * 0.9f)
                    lineTo(size.width * 0.58f, size.height * 0.63f)
                    lineTo(size.width * 0.42f, size.height * 0.63f)
                    lineTo(size.width * 0.42f, size.height * 0.9f)
                    lineTo(size.width * 0.18f, size.height * 0.9f)
                    lineTo(size.width * 0.18f, size.height * 0.48f)
                    close()
                }

                drawPath(path, color)
            }

            1 -> {
                drawRoundRect(
                    color = color,
                    topLeft = Offset(
                        size.width * 0.15f,
                        size.height * 0.15f
                    ),
                    size = androidx.compose.ui.geometry.Size(
                        size.width * 0.7f,
                        size.height * 0.7f
                    ),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(
                        4.dp.toPx()
                    ),
                    style = Stroke(strokeWidth)
                )

                drawLine(
                    color,
                    Offset(size.width * 0.15f, size.height * 0.42f),
                    Offset(size.width * 0.85f, size.height * 0.42f),
                    strokeWidth
                )
            }

            2 -> {
                drawCircle(
                    color,
                    radius = size.width * 0.23f,
                    center = Offset(
                        size.width * 0.5f,
                        size.height * 0.3f
                    )
                )

                drawArc(
                    color = color,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(
                        size.width * 0.15f,
                        size.height * 0.42f
                    ),
                    size = androidx.compose.ui.geometry.Size(
                        size.width * 0.7f,
                        size.height * 0.55f
                    ),
                    style = Stroke(strokeWidth)
                )
            }

            3 -> {
                drawCircle(
                    color = color,
                    radius = size.width * 0.23f,
                    center = Offset(
                        size.width * 0.5f,
                        size.height * 0.3f
                    ),
                    style = Stroke(strokeWidth)
                )

                drawArc(
                    color = color,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(
                        size.width * 0.12f,
                        size.height * 0.42f
                    ),
                    size = androidx.compose.ui.geometry.Size(
                        size.width * 0.76f,
                        size.height * 0.55f
                    ),
                    style = Stroke(strokeWidth)
                )
            }
        }
    }
}

// Icono del buscador
@Composable
private fun SearchIcon(
    modifier: Modifier = Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = color,
            radius = size.width * 0.31f,
            center = Offset(
                size.width * 0.42f,
                size.height * 0.42f
            ),
            style = Stroke(width = 2.dp.toPx())
        )

        drawLine(
            color = color,
            start = Offset(
                size.width * 0.65f,
                size.height * 0.65f
            ),
            end = Offset(
                size.width * 0.9f,
                size.height * 0.9f
            ),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

// Icono del carrito
@Composable
private fun CartIcon(
    modifier: Modifier = Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val stroke = 2.dp.toPx()

        drawLine(
            color,
            Offset(size.width * 0.12f, size.height * 0.18f),
            Offset(size.width * 0.28f, size.height * 0.18f),
            stroke,
            cap = StrokeCap.Round
        )

        val basket = Path().apply {
            moveTo(size.width * 0.28f, size.height * 0.18f)
            lineTo(size.width * 0.38f, size.height * 0.68f)
            lineTo(size.width * 0.82f, size.height * 0.68f)
            lineTo(size.width * 0.91f, size.height * 0.32f)
            lineTo(size.width * 0.32f, size.height * 0.32f)
        }

        drawPath(
            path = basket,
            color = color,
            style = Stroke(
                width = stroke,
                cap = StrokeCap.Round
            )
        )

        drawCircle(
            color,
            radius = size.width * 0.055f,
            center = Offset(
                size.width * 0.45f,
                size.height * 0.85f
            )
        )

        drawCircle(
            color,
            radius = size.width * 0.055f,
            center = Offset(
                size.width * 0.77f,
                size.height * 0.85f
            )
        )
    }
}
