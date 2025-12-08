package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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

data class GridItemData(
    val titulo: String,
    val descripcion1: String,
    val descripcion2: String,
    val route: String
)

@Composable
fun HomeScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoRegular = FontFamily(Font(R.font.lato_regular))

    val grid1 = listOf(
        GridItemData(
            "Tienda Online!",
            "Bienvenido a nuestra tienda en versión móvil!",
            "Presiona el botón para ver nuestros productos disponibles.",
            "productos"
        ),
        GridItemData(
            "Conoce nuestros ingredientes emblemáticos",
            "Descubre los secretos detrás de los sabores únicos de nuestra pastelería.",
            "Explora cada ingrediente estrella y su importancia en nuestras recetas.",
            "blogUno"
        )
    )

    val grid2 = listOf(
        GridItemData(
            "Un hito en la pastelería chilena",
            "Sumérgete en la historia de la pastelería nacional y sus grandes momentos.",
            "Conoce los detalles de este importante legado dulce de Chile.",
            "blogDos"
        ),
        GridItemData(
            "¡¡Ofertas Especiales!!",
            "Aprovecha nuestras promociones exclusivas por tiempo limitado.",
            "Descuentos irresistibles en productos seleccionados.",
            "ofertas"
        )
    )

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
            // Primera grilla
            SectionGrid(
                items = grid1,
                navController = navController,
                Pacifico = Pacifico,
                Lato = LatoRegular,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(340.dp) // ajustar según el contenido
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Segunda grilla
            SectionGrid(
                items = grid2,
                navController = navController,
                Pacifico = Pacifico,
                Lato = LatoRegular,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(340.dp) // ajustar según el contenido
            )
        }
    }
}

/* ---------- GRILLA REUTILIZABLE ---------- */
@Composable
private fun SectionGrid(
    items: List<GridItemData>,
    navController: NavHostController,
    Pacifico: FontFamily,
    Lato: FontFamily,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // o GridCells.Adaptive(160.dp)
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            InfoCard(
                titulo = item.titulo,
                descripcion1 = item.descripcion1,
                descripcion2 = item.descripcion2,
                onClick = { navController.navigate(item.route) },
                Pacifico = Pacifico,
                Lato = Lato,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/* ---------- COMPONENTE REUTILIZABLE PARA LAS TARJETAS (ahora con modifier) ---------- */
@Composable
fun InfoCard(
    titulo: String,
    descripcion1: String,
    descripcion2: String,
    onClick: () -> Unit,
    Pacifico: FontFamily,
    Lato: FontFamily,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = ColorCard),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = titulo,
                fontFamily = Pacifico,
                color = ColorTitulos,
                fontSize = 20.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            //Descripción
            Text(
                text = descripcion1,
                fontSize = 13.sp,
                color = ColorTexto,
                fontFamily = Lato,
                modifier = Modifier.padding(bottom = 4.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Text(
                text = descripcion2,
                fontSize = 13.sp,
                color = ColorTexto,
                fontFamily = Lato,
                modifier = Modifier.padding(bottom = 12.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            // Botón
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
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
