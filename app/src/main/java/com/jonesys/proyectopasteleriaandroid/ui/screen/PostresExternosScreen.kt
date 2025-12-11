package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.model.Meal
import com.jonesys.proyectopasteleriaandroid.ui.components.ScreenWithDrawer
import com.jonesys.proyectopasteleriaandroid.ui.theme.*
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.MealViewModel

@Composable
fun PostresExternosScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    mealViewModel: MealViewModel = viewModel()
) {
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoRegular = FontFamily(Font(R.font.lato_regular))
    val dessertMeals by mealViewModel.dessertMeals.collectAsState()
    val loading by mealViewModel.loading.collectAsState()

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
        ) {
            // Header
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
                    Text(
                        text = "Postres Internacionales",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorMainBlanco,
                        fontFamily = Pacifico
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Descubre deliciosos postres del mundo",
                        fontSize = 16.sp,
                        color = ColorMainBlanco,
                        fontFamily = LatoRegular
                    )
                }
            }

            // Content
            when {
                loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = ColorBotonRosa)
                    }
                }
                dessertMeals.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay postres disponibles",
                            color = ColorTexto,
                            fontSize = 18.sp,
                            fontFamily = LatoRegular
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(dessertMeals) { meal ->
                            PostreExternoCard(meal = meal)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PostreExternoCard(meal: Meal) {
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
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = meal.strMeal,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = meal.strMeal,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorTitulos
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "ID: ${meal.idMeal}",
                    fontSize = 12.sp,
                    color = ColorTexto
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = ColorBotonRosa,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
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
