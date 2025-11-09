package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.ui.components.Header
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(ColorMainBeige)
    ) {
        Header(navController = navController)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = ColorMainBeige),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    "Tienda Online!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorTexto
                )
                Text(
                    "Bienvenido a nuestra tienda en versión móvil! Conoce nuestra variedad en pasteles y postres!",
                    fontSize = 13.sp,
                    color = ColorTexto
                )
            }
        }
    }
}