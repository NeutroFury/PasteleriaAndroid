package com.jonesys.proyectopasteleriaandroid

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.jonesys.proyectopasteleriaandroid.ui.screen.LoginScreen
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.LoginViewModel
import org.junit.*

class LoginTest {
    @get:Rule
    val composableTest = createComposeRule()

    // Verificar que todos los elementos de la pantalla de login se muestran correctamente
    @Test
    fun pantallLogin_muestraTodosLosElementos() {
        composableTest.setContent {
            val navController = rememberNavController()
            LoginScreen(
                viewModel = LoginViewModel(),
                authViewModel = AuthViewModel(),
                navController = navController
            )
        }

        composableTest.onNodeWithText("Iniciar Sesión").assertExists()
        composableTest.onNodeWithText("Correo electrónico").assertExists()
        composableTest.onNodeWithText("Contraseña").assertExists()
        composableTest.onNodeWithText("Ingresar").assertExists()
        composableTest.onNodeWithText("Regístrate aquí", substring = true).assertExists()
        composableTest.onNodeWithText("Continuar como invitado").assertExists()
    }
    // Verificar que los campos de texto permitan ingresar informacion y que el botón de login está presente
    @Test
    fun pantallLogin_permiteIngresarCredenciales() {
        composableTest.setContent {
            val navController = rememberNavController()
            LoginScreen(
                viewModel = LoginViewModel(),
                authViewModel = AuthViewModel(),
                navController = navController
            )
        }
        composableTest.onNodeWithText("Correo electrónico").performTextInput("usuario@ejemplo.com")
        composableTest.onNodeWithText("Contraseña").performTextInput("miPassword123")
        composableTest.onNodeWithText("Ingresar").assertExists().assertHasClickAction()
    }

    @Test
    fun presionar_boton_Ingresar() {
        composableTest.setContent {
            val navController = rememberNavController()
            LoginScreen(
                viewModel = LoginViewModel(),
                authViewModel = AuthViewModel(),
                navController = navController
            )
        }

        val btnIngresar = composableTest.onNodeWithTag("BTN_LOGIN")
        btnIngresar.assertExists().assertHasClickAction()
        btnIngresar.performClick()

    }

}
