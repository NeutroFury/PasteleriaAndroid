package com.jonesys.proyectopasteleriaandroid
import androidx.compose.ui.test.assertHasClickAction
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.jonesys.proyectopasteleriaandroid.ui.screen.ProductosScreen
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.CarritoViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.ProductosViewModel
import org.junit.Rule
import org.junit.Test

class ProductoTest2 {

    @get:Rule
    val composableTest = createComposeRule()

    @Test
    fun presionar_boton_agregar_producto() {
        composableTest.setContent {
            val navController = rememberNavController()
            ProductosScreen(
                vm = ProductosViewModel(),
                authViewModel = AuthViewModel(),
                navController = navController,
                carritoViewModel = CarritoViewModel()
            )
        }
        val btnagregar = composableTest.onAllNodesWithTag("BTN_AñadirCarrito")[0].performClick()
        btnagregar.assertExists().assertHasClickAction()
        btnagregar.performClick()
    }
}