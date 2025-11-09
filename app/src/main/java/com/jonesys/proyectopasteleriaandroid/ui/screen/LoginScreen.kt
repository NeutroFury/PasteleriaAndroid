// kotlin
package com.jonesys.proyectopasteleriaandroid.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavHostController
) {
    val uiState by viewModel.FormData.collectAsState()
    val contexto = LocalContext.current

    LaunchedEffect(uiState.isLogin) {
        if (uiState.isLogin) {
            navController.navigate("bienvenida") {
                popUpTo("login") { inclusive = true }
                launchSingleTop = true
            }
            Toast.makeText(contexto, "Inició Sesión Correctamente", Toast.LENGTH_LONG).show()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorMainBeige) // fondo primero
            .statusBarsPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.actualizarEmail(it) },
            label = { Text("Email") },
            isError = uiState.error != null,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.actualizarPassword(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.error != null,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        uiState.error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(8.dp))
        }
        Button(onClick = { viewModel.Login() }) {
            Text("Iniciar Sesión")
        }
    }
}