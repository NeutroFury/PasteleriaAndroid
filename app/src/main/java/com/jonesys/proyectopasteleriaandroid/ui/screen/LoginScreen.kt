package com.jonesys.proyectopasteleriaandroid.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.LoginViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val uiState by viewModel.FormData.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            uiState.isLogin -> {
                Text("Inició Sesión Correctamente")
            }
            else -> {
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.actualizarEmail(it) },
                    label = { Text("Email") }
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { viewModel.actualizarPassword(it) },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(20.dp))
                uiState.error?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Button(
                    onClick = {
                        viewModel.Login()
                    }
                ) {
                    Text("Iniciar Sesión")
                }
            }
        }
    }
}
