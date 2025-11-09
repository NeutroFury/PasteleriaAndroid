// Kotlin
package com.jonesys.proyectopasteleriaandroid.navegate

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonesys.proyectopasteleriaandroid.ui.screen.Fondo
import com.jonesys.proyectopasteleriaandroid.ui.screen.HomeScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.LoginScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.RegistroScreen
import com.jonesys.proyectopasteleriaandroid.viewmodel.LoginViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.RegistroViewModel

@Composable
fun AppNavigate() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()

    NavHost(navController = navController, startDestination = "bienvenida") {
        composable("bienvenida") { HomeScreen(navController) }
        composable("login") {
            Box(Modifier.fillMaxSize()) {
                Fondo()
                LoginScreen(viewModel = loginViewModel, navController = navController)
            }
        }
        composable("registro") {
            val registroViewModel: RegistroViewModel = viewModel()
            Box(Modifier.fillMaxSize()) {
                Fondo()
                RegistroScreen(
                    viewModel = registroViewModel,
                    navController = navController
                )
            }
        }
    }
}
