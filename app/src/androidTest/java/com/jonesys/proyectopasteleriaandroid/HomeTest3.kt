package com.jonesys.proyectopasteleriaandroid

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonesys.proyectopasteleriaandroid.ui.screen.HomeScreen
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.ProductosViewModel
import org.junit.Rule
import org.junit.Test

class HomeTest3 {
    @get:Rule
    val composableTest = createComposeRule()

    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun presionar_boton_ver_productos() {
        composableTest.setContent {
            val navController = rememberNavController()


            NavHost(navController = navController, startDestination = "home") {


                composable("home") {
                    HomeScreen(
                        authViewModel = AuthViewModel(),
                        navController = navController,
                        productosViewModel = ProductosViewModel()
                    )
                }


                composable("productos") {
                    Text("Pantalla Productos Fake")
                }
            }
        }

        val btnproductos = composableTest.onNodeWithTag("BTN_VerProductos")
        btnproductos.assertExists().assertHasClickAction()


        btnproductos.performClick()
    }
}
