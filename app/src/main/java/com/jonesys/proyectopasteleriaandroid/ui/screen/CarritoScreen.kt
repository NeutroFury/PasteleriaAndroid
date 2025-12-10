package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.model.CarritoItem
import com.jonesys.proyectopasteleriaandroid.ui.components.ScreenWithDrawer
import com.jonesys.proyectopasteleriaandroid.ui.theme.*
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.CarritoViewModel

@Composable
fun CarritoScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    carritoViewModel: CarritoViewModel = viewModel()
) {
    val userId by authViewModel.userId.collectAsState()
    val carritoItems by carritoViewModel.carritoItems.collectAsState()

    LaunchedEffect(userId) {
        userId?.let {
            carritoViewModel.cargarCarrito(it)
        }
    }

    val total = carritoItems.sumOf {
        (it.producto?.precio ?: 0.0) * it.cantidad
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
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "Mi Carrito",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = ColorTitulos,
                textAlign = TextAlign.Center
            )

            if (carritoItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Tu carrito está vacío",
                        style = MaterialTheme.typography.bodyLarge,
                        color = ColorTexto
                    )
                }
            } else {
                for (item in carritoItems) {
                    CarritoItemCard(
                        item = item,
                        onIncrement = {
                            carritoViewModel.actualizarCantidad(
                                item.id!!,
                                item.cantidad + 1
                            )
                        },
                        onDecrement = {
                            if (item.cantidad > 1) {
                                carritoViewModel.actualizarCantidad(
                                    item.id!!,
                                    item.cantidad - 1
                                )
                            }
                        },
                        onDelete = {
                            carritoViewModel.eliminarItem(item.id!!)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

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
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total:",
                                style = MaterialTheme.typography.headlineMedium,
                                color = ColorTitulos,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$${total.toInt()}",
                                style = MaterialTheme.typography.headlineMedium,
                                color = ColorTitulos,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = ColorBotonRosa),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(
                                text = "Proceder al pago",
                                color = ColorMainBlanco,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun CarritoItemCard(
    item: CarritoItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = ColorMainBlanco),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val context = LocalContext.current
            val imageResId = remember(item.producto?.imagenUrl) {
                val name = item.producto?.imagenUrl?.substringBeforeLast(".") ?: ""
                context.resources.getIdentifier(name, "drawable", context.packageName)
            }

            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = item.producto?.nombre,
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
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No image", color = ColorTexto, fontSize = 10.sp)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.producto?.nombre ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    color = ColorTitulos,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$${item.producto?.precio?.toInt() ?: 0}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = ColorTexto,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = onDecrement,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Text(
                            "-",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = ColorTexto
                        )
                    }

                    Text(
                        text = "${item.cantidad}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    IconButton(
                        onClick = onIncrement,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Aumentar",
                            tint = ColorTexto
                        )
                    }

                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = ColorBotonRosa
                        )
                    }
                }
            }
        }
    }
}
