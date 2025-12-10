package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.ui.components.ScreenWithDrawer
import com.jonesys.proyectopasteleriaandroid.ui.theme.*
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.ProductosViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.CarritoViewModel
import com.jonesys.proyectopasteleriaandroid.model.Producto

@Composable
fun ProductosScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    vm: ProductosViewModel = viewModel(),
    carritoViewModel: CarritoViewModel = viewModel()
) {
    val userId by authViewModel.userId.collectAsState()
    val productos by vm.productos.collectAsState(initial = emptyList<Producto>())

    LaunchedEffect(Unit) {
        vm.cargarProductos()
    }

    ScreenWithDrawer(
        navController = navController,
        authViewModel = authViewModel
    ) { innerPadding ->
        Scaffold(
            containerColor = ColorMainBeige,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("carrito") },
                    containerColor = ColorBotonRosa,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Ir al carrito",
                        tint = ColorMainBlanco
                    )
                }
            }
        ) { scaffoldPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .background(ColorMainBeige)
                    .padding(innerPadding)
                    .padding(scaffoldPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    "Productos",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = ColorTitulos,
                    textAlign = TextAlign.Center
                )

                for (producto in productos) {
                    ProductoCard(
                        producto = producto,
                        onAddToCart = {
                            userId?.let { id ->
                                carritoViewModel.agregarProductoAlCarrito(
                                    usuarioId = id,
                                    producto = producto,
                                    cantidad = 1
                                )
                            }
                        }
                    )
                }

                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ProductoCard(producto: Producto, onAddToCart: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = ColorMainBlanco),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            val context = LocalContext.current
            val imageResId = remember(producto.imagenUrl) {
                val name = producto.imagenUrl.substringBeforeLast(".")
                context.resources.getIdentifier(name, "drawable", context.packageName)
            }

            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = producto.nombre,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No image", color = ColorTexto)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleLarge,
                color = ColorTitulos,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = producto.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                color = ColorTexto,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "$${producto.precio.toInt()}",
                style = MaterialTheme.typography.headlineMedium,
                color = ColorTitulos,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onAddToCart,
                colors = ButtonDefaults.buttonColors(containerColor = ColorBotonRosa),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text(
                    text = "Añadir al carrito",
                    color = ColorMainBlanco,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
