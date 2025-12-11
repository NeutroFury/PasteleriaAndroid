package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.model.Producto
import com.jonesys.proyectopasteleriaandroid.ui.components.ScreenWithDrawer
import com.jonesys.proyectopasteleriaandroid.ui.theme.*
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.ProductosViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    productosViewModel: ProductosViewModel = viewModel()
) {
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoRegular = FontFamily(Font(R.font.lato_regular))
    val LatoBlack = FontFamily(Font(R.font.lato_black_italic))
    val productos by productosViewModel.productos.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        productosViewModel.cargarProductos()
    }

    ScreenWithDrawer(
        navController = navController,
        authViewModel = authViewModel
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorMainBeige)
                .padding(innerPadding)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {
            // Banner Principal
            BannerPrincipal(
                navController = navController,
                Pacifico = Pacifico,
                Lato = LatoRegular,
                LatoBlack = LatoBlack
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Productos Destacados
            SeccionProductosDestacados(
                navController = navController,
                Pacifico = Pacifico,
                productos = productos.take(4)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nuestro Blog
            SeccionBlog(navController = navController, Pacifico = Pacifico, Lato = LatoRegular)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun BannerPrincipal(
    navController: NavHostController,
    Pacifico: FontFamily,
    Lato: FontFamily,
    LatoBlack: FontFamily
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = ColorMainRosa),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(220.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Las mejores recetas de pastelería chilena hechas con amor",
                fontFamily = LatoBlack,
                color = ColorMainBlanco,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )


            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("productos") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorMainBlanco,
                    contentColor = ColorMainRosa
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.height(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ver Productos",
                    fontFamily = Pacifico,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SeccionProductosDestacados(
    navController: NavHostController,
    Pacifico: FontFamily,
    productos: List<Producto>
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Productos Destacados",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = ColorTitulos,
                fontFamily = Pacifico
            )
            TextButton(onClick = { navController.navigate("productos") }) {
                Text("Ver todos", color = ColorBotonRosa)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (productos.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = ColorMainBlanco),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = ColorBotonRosa)
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                productos.forEach { producto ->
                    ProductoDestacadoCard(producto = producto)
                }
            }
        }
    }
}

@Composable
private fun ProductoDestacadoCard(producto: Producto) {
    val context = LocalContext.current
    val imageResId = remember(producto.imagenUrl) {
        val name = producto.imagenUrl.substringBeforeLast(".")
        context.resources.getIdentifier(name, "drawable", context.packageName)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = ColorMainBlanco),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = producto.nombre,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(ColorCard),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = ColorTexto,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = producto.nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorTitulos
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = producto.descripcion,
                    fontSize = 14.sp,
                    color = ColorTexto,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = ColorBotonRosa,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "$${producto.precio.toInt()}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ColorBotonRosa
            )
        }
    }
}

@Composable
private fun SeccionBlog(navController: NavHostController, Pacifico: FontFamily, Lato: FontFamily) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Descubre Más",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = ColorTitulos,
            fontFamily = Pacifico
        )

        Spacer(modifier = Modifier.height(12.dp))

        BlogCard(
            titulo = "Ingredientes Emblemáticos",
            descripcion = "Conoce los secretos de nuestra pastelería tradicional",
            onClick = { navController.navigate("blogUno") },
            Lato = Lato
        )

        Spacer(modifier = Modifier.height(12.dp))

        BlogCard(
            titulo = "Historia de la Pastelería",
            descripcion = "Un recorrido por la tradición dulce de Chile",
            onClick = { navController.navigate("blogDos") },
            Lato = Lato
        )
    }
}

@Composable
private fun BlogCard(
    titulo: String,
    descripcion: String,
    onClick: () -> Unit,
    Lato: FontFamily
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = ColorCard),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorTitulos,
                    fontFamily = Lato
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = descripcion,
                    fontSize = 14.sp,
                    color = ColorTexto,
                    fontFamily = Lato
                )
            }
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = ColorBotonRosa,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
