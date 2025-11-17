package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.model.ProductUi
import com.jonesys.proyectopasteleriaandroid.ui.components.Footer
import com.jonesys.proyectopasteleriaandroid.ui.components.Header
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorCard
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.ProductosViewModel

@Composable
fun ProductosScreen(navController: NavHostController, authViewModel: AuthViewModel, vm: ProductosViewModel = viewModel()) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    val productos by vm.productosUi.collectAsState()
    Scaffold(
        containerColor = ColorMainBeige,
        topBar = { Header(navController = navController, isLogged = isLogged, userName = userName) },
        bottomBar = { Footer(navController = navController) }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(ColorMainBeige)
                .padding(innerPadding)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "Productos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = ColorTexto,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            productos.forEach { ProductCard(product = it) }
            Spacer(Modifier.height(12.dp))
        }
    }
}


@Composable
fun ProductCard(product: ProductUi) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = ColorCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(product.imagenRes),
                    contentDescription = product.titulo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.Crop
                )
                if (product.tieneDescuento && product.descuentoPercent != null) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(ColorTexto.copy(alpha = 0.35f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Text(
                            "-${product.descuentoPercent}%",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ColorTexto
                        )
                    }
                }
            }
            Column(Modifier.padding(12.dp)) {
                Text(product.titulo, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ColorTexto)
                Spacer(Modifier.height(4.dp))
                Text(product.descripcion, fontSize = 13.sp, lineHeight = 15.sp, color = ColorTexto)
                Spacer(Modifier.height(8.dp))
                if (product.tieneDescuento) {
                    Text(
                        product.precioBaseFormateado,
                        fontSize = 13.sp,
                        color = ColorTexto.copy(alpha = 0.6f),
                        textDecoration = TextDecoration.LineThrough
                    )
                }
                Text(
                    product.precioFinalFormateado,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorTexto
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = { /* Acción agregar */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorTexto.copy(alpha = 0.35f),
                        contentColor = ColorTexto
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text("Agregar", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
