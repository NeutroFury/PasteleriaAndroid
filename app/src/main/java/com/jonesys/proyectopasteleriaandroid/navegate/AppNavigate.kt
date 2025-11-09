package com.jonesys.proyectopasteleriaandroid.navegate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonesys.proyectopasteleriaandroid.ui.screen.Fondo
import com.jonesys.proyectopasteleriaandroid.ui.screen.HomeScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.LoginScreen
import com.jonesys.proyectopasteleriaandroid.viewmodel.LoginViewModel


@Composable
fun AppNavigate() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "bienvenida"
    ) {
        composable("bienvenida") {
            HomeScreen(navController = navController)
        }
        composable("login") {
            Fondo()
            LoginScreen(viewModel = loginViewModel, navController = navController)
        }
    }
}
