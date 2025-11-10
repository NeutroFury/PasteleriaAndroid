package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.ui.components.Footer
import com.jonesys.proyectopasteleriaandroid.ui.components.Header
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorCard
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainRosa
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel

@Composable
fun HomeScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()

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
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = ColorCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Tienda Online!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto,

                        )
                    Text(
                        "Bienvenido a nuestra tienda en versión móvil!",
                        fontSize = 13.sp,
                        color = ColorTexto,)
                    Text(
                        "Apreta el boton para ver nuestros productos disponibles",
                        fontSize = 13.sp,
                        color = ColorTexto,)
                    Button(
                        onClick = { navController.navigate("productos") },
                        modifier = Modifier.height(36.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorTexto,
                            contentColor = ColorMainRosa
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("IR!", fontSize = 13.sp)
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = ColorCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Conoce sobre aquellos emblematicos ingredientes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto,

                        )
                    Text(
                        "Lee a detalle sobre nuestros ingredientes estrella en nuestras preparaciones",
                        fontSize = 13.sp,
                        color = ColorTexto,)
                    Button(
                        onClick = { navController.navigate("blogUno") },
                        modifier = Modifier.height(36.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorTexto,
                            contentColor = ColorMainRosa
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("IR!", fontSize = 13.sp)
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = ColorCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Conoce sobre este historico hito en la pasteleria chilena!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto,

                        )
                    Text(
                        "Lee a detalle sobre este importante suceso en la historia de la pasteleria chilena",
                        fontSize = 13.sp,
                        color = ColorTexto,)
                    Button(
                        onClick = { navController.navigate("blogDos") },
                        modifier = Modifier.height(36.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorTexto,
                            contentColor = ColorMainRosa
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("IR!", fontSize = 13.sp)
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = ColorCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "¡¡OFERTAS ESPECIALES!!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(25.dp))
                    Button(
                        onClick = { navController.navigate("ofertas") },
                        modifier = Modifier.height(36.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorTexto,
                            contentColor = ColorMainRosa
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("IR!", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}