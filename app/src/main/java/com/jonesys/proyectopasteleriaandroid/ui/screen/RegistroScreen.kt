// Kotlin
package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.viewmodel.RegistroViewModel

@Composable
fun RegistroScreen(viewModel: RegistroViewModel, navController: NavController) {
    val usuario = viewModel.usuario.collectAsState().value
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorMainBeige)
            .statusBarsPadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = usuario.nombre,
            onValueChange = viewModel::onChangeNombre,
            label = { Text("Nombre") },
            isError = usuario.error.nombre != null,
            supportingText = { usuario.error.nombre?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = usuario.email,
            onValueChange = viewModel::onChangeEmail,
            label = { Text("Correo") },
            isError = usuario.error.email != null,
            supportingText = { usuario.error.email?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = usuario.password,
            onValueChange = viewModel::onChangePassword,
            label = { Text("Password") },
            isError = usuario.error.password != null,
            visualTransformation = PasswordVisualTransformation(),
            supportingText = { usuario.error.password?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = usuario.confirmarPassword,
            onValueChange = viewModel::onChangeConfirmar,
            label = { Text("Confirmar Password") },
            isError = usuario.error.confirmarPassword != null,
            visualTransformation = PasswordVisualTransformation(),
            supportingText = { usuario.error.confirmarPassword?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Checkbox(
                checked = usuario.aceptarTerminos,
                onCheckedChange = viewModel::onChangeAceptarTerminos
            )
            Text("Acepta los términos")
        }
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                if (viewModel.validar()) {
                    navController.navigate("bienvenida")
                } else {
                    Toast.makeText(contexto, "Validación fallida", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Registrarse")
        }
    }
}
