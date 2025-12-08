// kotlin
package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.ui.components.Footer
import com.jonesys.proyectopasteleriaandroid.ui.components.Header
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.ProductosViewModel
import com.jonesys.proyectopasteleriaandroid.model.Producto
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

@Composable
fun ProductosScreen(navController: NavHostController, authViewModel: AuthViewModel, vm: ProductosViewModel = viewModel()) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()

    val productos by vm.productos.collectAsState(initial = emptyList<Producto>())

    LaunchedEffect(Unit) {
        vm.cargarProductos()
    }

    Scaffold(
        containerColor = ColorMainBeige,
        topBar = { Header(navController = navController, isLogged = isLogged, userName = userName) },
        bottomBar = { Footer(navController = navController) }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(ColorMainBeige)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "Productos",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            for (producto in productos) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(text = producto.toString(), color = ColorTexto)
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}
