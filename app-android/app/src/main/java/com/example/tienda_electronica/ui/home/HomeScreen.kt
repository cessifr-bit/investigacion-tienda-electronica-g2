
package com.example.tienda_electronica.ui.home
import com.example.tienda_electronica.ui.theme.AzulNoche
import com.example.tienda_electronica.ui.theme.AzulSuperficie
import com.example.tienda_electronica.ui.theme.AzulTarjeta
import com.example.tienda_electronica.ui.theme.AzulElectrico
import com.example.tienda_electronica.ui.theme.AzulBrillante
import com.example.tienda_electronica.ui.theme.AzulCielo
import com.example.tienda_electronica.ui.theme.AzulCyan
import com.example.tienda_electronica.ui.theme.Blanco
import com.example.tienda_electronica.ui.theme.GrisAzulado
import com.example.tienda_electronica.ui.theme.GrisClaro
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

private data class BannerItem(
    val label: String,
    val title: String,
    val description: String,
    val image: String
)

private data class CategoryItem(
    val name: String,
    val image: String
)

private data class ProductItem(
    val name: String,
    val description: String,
    val image: String
)

private val banners = listOf(
    BannerItem(
        label = "TECNOLOGÍA PARA TI",
        title = "Descubre lo nuevo",
        description = "Encuentra tecnología para cada momento.",
        image = "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=1000"
    ),
    BannerItem(
        label = "SONIDO Y CALIDAD",
        title = "Escucha diferente",
        description = "Descubre nuevas experiencias de sonido.",
        image = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=1000"
    ),
    BannerItem(
        label = "POTENCIA Y RENDIMIENTO",
        title = "Lleva más lejos tus ideas",
        description = "Explora nuestra selección tecnológica.",
        image = "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=1000"
    )
)

private val categories = listOf(
    CategoryItem(
        "Celulares",
        "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=400"
    ),
    CategoryItem(
        "Audífonos",
        "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400"
    ),
    CategoryItem(
        "Laptops",
        "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400"
    ),
    CategoryItem(
        "Smartwatch",
        "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400"
    ),
    CategoryItem(
        "Gaming",
        "https://images.unsplash.com/photo-1593305841991-05c297ba4575?w=400"
    )
)

private val products = listOf(
    ProductItem(
        "Audífonos inalámbricos",
        "Audio y conectividad",
        "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=600"
    ),
    ProductItem(
        "Laptop",
        "Rendimiento y productividad",
        "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=600"
    ),
    ProductItem(
        "Smartwatch",
        "Tecnología para tu día",
        "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600"
    ),
    ProductItem(
        "Consola gaming",
        "Entretenimiento y diversión",
        "https://images.unsplash.com/photo-1593305841991-05c297ba4575?w=600"
    )
)

@Composable
fun HomeScreen(
    onCatalogClick: () -> Unit = {},
    onCartClick: () -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    var selectedNavigation by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = AzulNoche,
        bottomBar = {
            HomeBottomNavigation(
                selectedItem = selectedNavigation,
                onItemSelected = {
                    selectedNavigation = it
                    if (it == 1) onCatalogClick()
                    if (it == 2) onCatalogClick()
                    if (it == 3) onCartClick()
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 20.dp)
        ) {

            HomeHeader(
                onCartClick = onCartClick
            )

            Spacer(Modifier.height(22.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                placeholder = {
                    Text(
                        "⌕  Buscar productos...",
                        color = GrisAzulado
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AzulElectrico,
                    unfocusedBorderColor = AzulSuperficie,
                    focusedTextColor = Blanco,
                    unfocusedTextColor = Blanco,
                    cursorColor = AzulCyan,
                    focusedContainerColor = AzulSuperficie,
                    unfocusedContainerColor = AzulSuperficie
                )
            )

            Spacer(Modifier.height(24.dp))

            PromotionCarousel(
                onCatalogClick = onCatalogClick
            )

            Spacer(Modifier.height(30.dp))

            SectionHeader(
                title = "Categorías destacadas",
                action = "Ver todas",
                onAction = onCatalogClick
            )

            Spacer(Modifier.height(15.dp))

            Row(
                modifier = Modifier.horizontalScroll(
                    rememberScrollState()
                ),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                categories.forEach { category ->
                    CategoryCard(category)
                }
            }

            Spacer(Modifier.height(30.dp))

            SectionHeader(
                title = "✦ Productos destacados",
                action = "Ver todos",
                onAction = onCatalogClick
            )

            Spacer(Modifier.height(15.dp))

            products.chunked(2).forEach { productRow ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    productRow.forEach { product ->
                        ProductCard(
                            product = product,
                            modifier = Modifier.weight(1f),
                            onAddToCart = onCartClick
                        )
                    }

                    if (productRow.size == 1) {
                        Spacer(Modifier.weight(1f))
                    }
                }

                Spacer(Modifier.height(14.dp))
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun HomeHeader(
    onCartClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "TIENDA",
                color = Blanco,
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "ELECTRÓNICA",
                color = AzulCielo,
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "Tecnología para ti",
                color = GrisAzulado,
                fontSize = 13.sp
            )
        }

        Surface(
            modifier = Modifier.clickable(
                onClick = onCartClick
            ),
            shape = RoundedCornerShape(50),
            color = AzulElectrico
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 17.dp,
                    vertical = 13.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🛒", fontSize = 19.sp)
                Spacer(Modifier.width(7.dp))
                Text(
                    "Carrito",
                    color = Blanco,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun PromotionCarousel(
    onCatalogClick: () -> Unit
) {
    val pagerState = rememberPagerState(
        pageCount = { banners.size }
    )

    LaunchedEffect(pagerState) {
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
                .height(225.dp),
            pageSpacing = 12.dp,
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                horizontal = 20.dp
            )
        ) { page ->

            val banner = banners[page]

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(22.dp))
                    .background(AzulSuperficie)
                    .clickable(onClick = onCatalogClick)
            ) {
                AsyncImage(
                    model = banner.image,
                    contentDescription = banner.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    AzulNoche.copy(alpha = 0.97f),
                                    AzulNoche.copy(alpha = 0.75f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .align(Alignment.CenterStart)
                        .padding(20.dp)
                ) {
                    Text(
                        banner.label,
                        color = AzulCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        banner.title,
                        color = Blanco,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 29.sp
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        banner.description,
                        color = GrisClaro,
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )

                    Spacer(Modifier.height(13.dp))

                    Button(
                        onClick = onCatalogClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AzulElectrico
                        ),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(
                            horizontal = 17.dp,
                            vertical = 8.dp
                        )
                    ) {
                        Text(
                            "Explorar →",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(banners.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(
                            width = if (pagerState.currentPage == index) 22.dp else 7.dp,
                            height = 7.dp
                        )
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index)
                                AzulElectrico
                            else
                                GrisAzulado.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    action: String,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            title,
            color = Blanco,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$action →",
            color = AzulCielo,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable(onClick = onAction)
        )
    }
}

@Composable
private fun CategoryCard(
    category: CategoryItem
) {
    Column(
        modifier = Modifier
            .width(125.dp)
            .clip(RoundedCornerShape(17.dp))
            .background(AzulTarjeta)
            .clickable { }
            .padding(9.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = category.image,
            contentDescription = category.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(9.dp))

        Text(
            category.name,
            color = Blanco,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(4.dp))
    }
}

@Composable
private fun ProductCard(
    product: ProductItem,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit
) {
    var isFavorite by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(19.dp),
        colors = CardDefaults.cardColors(
            containerColor = AzulTarjeta
        )
    ) {
        Column(
            modifier = Modifier.padding(11.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(13.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = if (isFavorite) "♥" else "♡",
                    color = if (isFavorite) AzulCielo else Blanco,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(7.dp)
                        .clickable {
                            isFavorite = !isFavorite
                        }
                )
            }

            Spacer(Modifier.height(10.dp))

            Text(
                product.name,
                color = Blanco,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(4.dp))

            Text(
                product.description,
                color = GrisAzulado,
                fontSize = 11.sp,
                maxLines = 2
            )

            Spacer(Modifier.height(12.dp))

            Text(
                "Consultar precio",
                color = AzulCyan,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(9.dp))

            Button(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulElectrico
                ),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = 5.dp,
                    vertical = 9.dp
                )
            ) {
                Text(
                    "🛒 Agregar",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun HomeBottomNavigation(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        "⌂" to "Inicio",
        "▦" to "Categorías",
        "♡" to "Favoritos",
        "♙" to "Perfil"
    )

    Surface(
        color = AzulSuperficie,
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp
        ),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items.forEachIndexed { index, item ->
                val selected = selectedItem == index

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onItemSelected(index)
                        }
                        .padding(vertical = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        item.first,
                        color = if (selected) AzulBrillante else GrisAzulado,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(3.dp))

                    Text(
                        item.second,
                        color = if (selected) AzulCielo else GrisAzulado,
                        fontSize = 10.sp,
                        fontWeight = if (selected)
                            FontWeight.Bold
                        else
                            FontWeight.Normal
                    )
                }
            }
        }
    }
}
