package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.components.Footer
import com.jonesys.proyectopasteleriaandroid.ui.components.Header
import com.jonesys.proyectopasteleriaandroid.ui.theme.*
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel

@Composable
fun HomeScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoRegular = FontFamily(Font(R.font.lato_regular))

    Scaffold(
        containerColor = ColorMainBeige,
        topBar = { Header(navController = navController, isLogged = isLogged, userName = userName) },
        bottomBar = { Footer(navController = navController) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorMainBeige)
                .padding(innerPadding)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {

            // Card 1 — Tienda Online
            InfoCard(
                titulo = "Tienda Online!",
                descripcion1 = "Bienvenido a nuestra tienda en versión móvil!",
                descripcion2 = "Presiona el botón para ver nuestros productos disponibles.",
                onClick = { navController.navigate("productos") },
                Pacifico = Pacifico,
                Lato = LatoRegular
            )

            // Card 2 — Ingredientes
            InfoCard(
                titulo = "Conoce nuestros ingredientes emblemáticos",
                descripcion1 = "Descubre los secretos detrás de los sabores únicos de nuestra pastelería.",
                descripcion2 = "Explora cada ingrediente estrella y su importancia en nuestras recetas.",
                onClick = { navController.navigate("blogUno") },
                Pacifico = Pacifico,
                Lato = LatoRegular
            )

            // Card 3 — Historia de la Pastelería
            InfoCard(
                titulo = "Un hito en la pastelería chilena",
                descripcion1 = "Sumérgete en la historia de la pastelería nacional y sus grandes momentos.",
                descripcion2 = "Conoce los detalles de este importante legado dulce de Chile.",
                onClick = { navController.navigate("blogDos") },
                Pacifico = Pacifico,
                Lato = LatoRegular
            )

            // Card 4 — Ofertas Especiales
            InfoCard(
                titulo = "¡¡Ofertas Especiales!!",
                descripcion1 = "Aprovecha nuestras promociones exclusivas por tiempo limitado.",
                descripcion2 = "Descuentos irresistibles en productos seleccionados.",
                onClick = { navController.navigate("ofertas") },
                Pacifico = Pacifico,
                Lato = LatoRegular
            )
        }
    }
}

/* ---------- COMPONENTE REUTILIZABLE PARA LAS TARJETAS ---------- */
@Composable
fun InfoCard(
    titulo: String,
    descripcion1: String,
    descripcion2: String,
    onClick: () -> Unit,
    Pacifico: FontFamily,
    Lato: FontFamily
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        colors = CardDefaults.cardColors(containerColor = ColorCard),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = titulo,
                fontFamily = Pacifico,
                color = ColorTitulos,
                fontSize = 24.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            //Descripción
            Text(
                text = descripcion1,
                fontSize = 14.sp,
                color = ColorTexto,
                fontFamily = Lato,
                modifier = Modifier.padding(bottom = 4.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Text(
                text = descripcion2,
                fontSize = 14.sp,
                color = ColorTexto,
                fontFamily = Lato,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            // Botón
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(40.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorTexto,
                    contentColor = ColorMainRosa
                )
            ) {
                Text(
                    text = "Ir",
                    fontSize = 15.sp,
                    fontFamily = Pacifico,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
