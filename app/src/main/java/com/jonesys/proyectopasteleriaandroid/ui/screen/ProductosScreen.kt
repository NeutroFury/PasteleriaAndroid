package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.components.Footer
import com.jonesys.proyectopasteleriaandroid.ui.components.Header
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorCard
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel

@Composable
fun ProductosScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    Scaffold(
        containerColor = ColorMainBeige,
        topBar = { Header(navController = navController, isLogged = isLogged, userName = userName) },
        bottomBar = { Footer(navController = navController) }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorMainBeige)
                .padding(innerPadding)
                .statusBarsPadding()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Productos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = ColorTexto,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Cards (ejemplo: repetir patrón existente)
            ProductoCard(
                imagen = R.drawable.pastel1,
                titulo = "Torta Cuadrada de Chocolate",
                descripcion = "Deliciosa torta de chocolate con capas de ganache y un toque de avellanas. Personalizable con mensajes especiales"
            )
            ProductoCard(
                imagen = R.drawable.pastel2,
                titulo = "Torta Cuadrada de Frutas",
                descripcion = "Una mezcla de frutas frescas y crema chantilly sobre un suave bizcocho de vainilla, ideal para celebraciones."
            )
            ProductoCard(
                imagen = R.drawable.pastel3,
                titulo = "Torta Circular de Vainilla",
                descripcion = "Bizcocho de vainilla clásico relleno con crema pastelera y cubierto con un glaseado dulce, perfecto para cualquier ocasión."
            )
            ProductoCard(
                imagen = R.drawable.pastel4,
                titulo = "Torta Circular de Manjar",
                descripcion = "Torta tradicional chilena con manjar y nueces, un deleite para los amantes de los sabores dulces y clásicos."
            )
            ProductoCard(
                imagen = R.drawable.pastel5,
                titulo = "Mousse de Chocolate",
                descripcion = "Postre individual cremoso y suave, hecho con chocolate de alta calidad, ideal para los amantes del chocolate."
            )
            ProductoCard(
                imagen = R.drawable.pastel6,
                titulo = "Tiramisú Clásico",
                descripcion = "Un postre italiano individual con capas de café, mascarpone y cacao, perfecto para finalizar cualquier comida."
            )
            ProductoCard(
                imagen = R.drawable.pastel7,
                titulo = "Torta Sin Azúcar de Naranja",
                descripcion = "Torta ligera y deliciosa, endulzada naturalmente, ideal para quienes buscan opciones más saludables."
            )
            ProductoCard(
                imagen = R.drawable.cheesecake,
                titulo = "Cheesecake Sin Azúcar",
                descripcion = "Suave y cremoso, este cheesecake es una opción perfecta para disfrutar sin culpa."
            )
            ProductoCard(
                imagen = R.drawable.pastel8,
                titulo = "Empanada de Manzana",
                descripcion = "Pastelería tradicional rellena de manzanas especiadas, perfecta para un dulce desayuno o merienda."
            )
            ProductoCard(
                imagen = R.drawable.pastel9,
                titulo = "Tarta de Santiago",
                descripcion = "Tradicional tarta española hecha con almendras, azúcar, y huevos, una delicia para los amantes de los postres clásicos."
            )
            ProductoCard(
                imagen = R.drawable.pastel10,
                titulo = "Brownie Sin Gluten",
                descripcion = "Rico y denso, este brownie es perfecto para quienes necesitan evitar el gluten sin sacrificar el sabor."
            )
            ProductoCard(
                imagen = R.drawable.pastel11,
                titulo = "Pan Sin Gluten",
                descripcion = "Suave y esponjoso, ideal para sándwiches o para acompañar cualquier comida."
            )
            ProductoCard(
                imagen = R.drawable.pastel12,
                titulo = "Torta Vegana de Chocolate",
                descripcion = "Torta de chocolate húmeda y deliciosa, hecha sin productos de origen animal, perfecta para veganos."
            )
            ProductoCard(
                imagen = R.drawable.pastel13,
                titulo = "Galletas Veganas de Avena",
                descripcion = "Crujientes y sabrosas, estas galletas son una excelente opción para un snack saludable y vegano."
            )
            ProductoCard(
                imagen = R.drawable.pastel14,
                titulo = "Torta Especial de Cumpleaños",
                descripcion = "Diseñada especialmente para celebraciones, personalizable con decoraciones y mensajes únicos."
            )
            ProductoCard(
                imagen = R.drawable.pastel15,
                titulo = "Torta Especial de Boda",
                descripcion = "Elegante y deliciosa, esta torta está diseñada para ser el centro de atención en cualquier boda."
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun ProductoCard(
    imagen: Int,
    titulo: String,
    descripcion: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = ColorCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = titulo,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorTexto
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    descripcion,
                    fontSize = 13.sp,
                    lineHeight = 15.sp,
                    color = ColorTexto
                )
            }
        }
    }
}