package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.ui.components.Footer
import com.jonesys.proyectopasteleriaandroid.ui.components.Header
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.OfertasViewModel

@Composable
fun OfertasScreen(navController: NavHostController, authViewModel: AuthViewModel,vm: OfertasViewModel = viewModel()) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    val userNombre by authViewModel.userNombre.collectAsState()

    Scaffold(
        containerColor = ColorMainBeige,
        topBar = { Header(navController = navController, isLogged = isLogged, userName = userName, userNombre = userNombre) },
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
                "Ofertas especiales",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = ColorTexto,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

        }
    }
}