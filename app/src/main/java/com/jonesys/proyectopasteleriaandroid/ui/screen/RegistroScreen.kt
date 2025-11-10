package com.jonesys.proyectopasteleriaandroid.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.theme.*
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.RegistroViewModel

@Composable
fun RegistroScreen(
    viewModel: RegistroViewModel,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    val usuario = viewModel.usuario.collectAsState().value
    val contexto = LocalContext.current
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoRegular = FontFamily(Font(R.font.lato_regular))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorMainBeige),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 🔹 Título
                Text(
                    text = "Registro de Usuario",
                    fontFamily = Pacifico,
                    color = ColorTitulos,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // 🔸 Campo Nombre
                OutlinedTextField(
                    value = usuario.nombre,
                    onValueChange = viewModel::onChangeNombre,
                    label = { Text("Nombre", fontFamily = LatoRegular) },
                    isError = usuario.error.nombre != null,
                    supportingText = {
                        usuario.error.nombre?.let {
                            Text(it, color = MaterialTheme.colorScheme.error, fontFamily = LatoRegular)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(Modifier.height(12.dp))

                // 🔸 Campo Correo
                OutlinedTextField(
                    value = usuario.email,
                    onValueChange = viewModel::onChangeEmail,
                    label = { Text("Correo electrónico", fontFamily = LatoRegular) },
                    placeholder = { Text("email@ejemplo.com", fontFamily = LatoRegular) },
                    isError = usuario.error.email != null,
                    supportingText = {
                        usuario.error.email?.let {
                            Text(it, color = MaterialTheme.colorScheme.error, fontFamily = LatoRegular)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(Modifier.height(12.dp))

                // 🔸 Campo Contraseña
                OutlinedTextField(
                    value = usuario.password,
                    onValueChange = viewModel::onChangePassword,
                    label = { Text("Contraseña", fontFamily = LatoRegular) },
                    placeholder = { Text("********", fontFamily = LatoRegular) },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = usuario.error.password != null,
                    supportingText = {
                        usuario.error.password?.let {
                            Text(it, color = MaterialTheme.colorScheme.error, fontFamily = LatoRegular)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(Modifier.height(12.dp))

                // 🔸 Confirmar contraseña
                OutlinedTextField(
                    value = usuario.confirmarPassword,
                    onValueChange = viewModel::onChangeConfirmar,
                    label = { Text("Confirmar contraseña", fontFamily = LatoRegular) },
                    placeholder = { Text("********", fontFamily = LatoRegular) },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = usuario.error.confirmarPassword != null,
                    supportingText = {
                        usuario.error.confirmarPassword?.let {
                            Text(it, color = MaterialTheme.colorScheme.error, fontFamily = LatoRegular)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(Modifier.height(12.dp))

                // 🔸 Checkbox de términos
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = usuario.aceptarTerminos,
                        onCheckedChange = viewModel::onChangeAceptarTerminos
                    )
                    Text(
                        "Acepto los términos y condiciones",
                        fontFamily = LatoRegular,
                        fontSize = 14.sp,
                        color = ColorTitulos
                    )
                }

                Spacer(Modifier.height(20.dp))

                // 🔹 Botón de registro
                Button(
                    onClick = {
                        if (viewModel.validar()) {
                            authViewModel.setLoggedIn(true, usuario.nombre)
                            navController.navigate("bienvenida")
                            Toast.makeText(contexto, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(contexto, "Verifique los campos", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(45.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorBotonRosa,
                        contentColor = ColorMainBlanco
                    )
                ) {
                    Text(
                        "Registrarse",
                        fontFamily = Pacifico,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
