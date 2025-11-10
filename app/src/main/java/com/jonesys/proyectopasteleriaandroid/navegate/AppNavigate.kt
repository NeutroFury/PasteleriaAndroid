// kotlin
package com.jonesys.proyectopasteleriaandroid.navegate

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonesys.proyectopasteleriaandroid.ui.screen.BlogDosScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.BlogUnoScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.CarritoScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.GeolocalizacionScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.HomeScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.LoginScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.OfertasScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.PerfilScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.ProductosScreen
import com.jonesys.proyectopasteleriaandroid.ui.screen.RegistroScreen
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.LoginViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.RegistroViewModel
import androidx.navigation.NavHostController

@Composable
fun AppNavigate() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "bienvenida") {
        composable("bienvenida") {
            Box(Modifier.fillMaxSize()) { HomeScreen(navController, authViewModel) }
        }
        composable("login") {
            Box(Modifier.fillMaxSize()) {
                LoginScreen(viewModel = loginViewModel, authViewModel = authViewModel, navController = navController)
            }
        }
        composable("registro") {
            val registroViewModel: RegistroViewModel = viewModel()
            Box(Modifier.fillMaxSize()) {
                RegistroScreen(
                    viewModel = registroViewModel,
                    authViewModel = authViewModel,
                    navController = navController
                )
            }
        }
        composable("productos") { Box(Modifier.fillMaxSize()) { ProductosScreen(navController, authViewModel)} }
        composable ("perfil"){ Box(Modifier.fillMaxSize()) { PerfilScreen(navController, authViewModel) } }
        composable("ofertas") { Box(Modifier.fillMaxSize()) { OfertasScreen(navController, authViewModel) } }
        composable("carrito") { Box(Modifier.fillMaxSize()) { CarritoScreen(navController, authViewModel) } }
        composable("blogUno") { Box(Modifier.fillMaxSize()) { BlogUnoScreen(navController, authViewModel) } }
        composable("blogDos") { Box(Modifier.fillMaxSize()) { BlogDosScreen(navController, authViewModel) } }
        composable("geo") { Box(Modifier.fillMaxSize()) { GeolocalizacionScreen(navController) } }
    }
}
