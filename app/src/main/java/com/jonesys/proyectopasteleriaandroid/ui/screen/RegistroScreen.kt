// Kotlin
package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.jonesys.proyectopasteleriaandroid.viewmodel.RegistroViewModel

@Composable
fun RegistroScreen(viewModel: RegistroViewModel, navController: NavController) {
    val usuario = viewModel.usuario.collectAsState().value
    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(25.dp)
    ) {
        OutlinedTextField(
            value = usuario.nombre,
            onValueChange = viewModel::onChangeNombre,
            label = { Text("Nombre") },
            isError = usuario.error.nombre != null,
            supportingText = { usuario.error.nombre?.let { Text(it, color = MaterialTheme.colorScheme.error) } }
        )
        Spacer(Modifier.height(18.dp))
        OutlinedTextField(
            value = usuario.email,
            onValueChange = viewModel::onChangeEmail,
            label = { Text("Correo") },
            isError = usuario.error.email != null,
            supportingText = { usuario.error.email?.let { Text(it, color = MaterialTheme.colorScheme.error) } }
        )
        Spacer(Modifier.height(18.dp))
        OutlinedTextField(
            value = usuario.password,
            onValueChange = viewModel::onChangePassword,
            label = { Text("Password") },
            isError = usuario.error.password != null,
            visualTransformation = PasswordVisualTransformation(),
            supportingText = { usuario.error.password?.let { Text(it, color = MaterialTheme.colorScheme.error) } }
        )
        Spacer(Modifier.height(18.dp))
        OutlinedTextField(
            value = usuario.confirmarPassword,
            onValueChange = viewModel::onChangeConfirmar,
            label = { Text("Confirmar Password") },
            isError = usuario.error.confirmarPassword != null,
            visualTransformation = PasswordVisualTransformation(),
            supportingText = { usuario.error.confirmarPassword?.let { Text(it, color = MaterialTheme.colorScheme.error) } }
        )
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(
                checked = usuario.aceptarTerminos,
                onCheckedChange = viewModel::onChangeAceptarTerminos
            )
            Text("Acepta los términos")
        }
        Button(onClick = {
            if (viewModel.validar()) {
                navController.navigate("bienvenida")
            } else {
                Toast.makeText(ctx, "Validación fallida", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Registrarse")
        }
    }
}
