package com.jonesys.proyectopasteleriaandroid.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorBotonRosa
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBlanco
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.LoginViewModel
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTitulos
import androidx.compose.material3.Text
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun LoginScreen(viewModel: LoginViewModel, authViewModel: AuthViewModel, navController: NavHostController) {
    val uiState by viewModel.FormData.collectAsState()
    val contexto = LocalContext.current
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoLight = FontFamily(Font(R.font.lato_light))

    LaunchedEffect(uiState.isLogin) {
        if (uiState.isLogin) {
            authViewModel.login(
                userId = uiState.userId,
                userName = uiState.email,
                userNombre = uiState.nombre ?: ""
            )
            navController.navigate("bienvenida") {
                popUpTo("login") { inclusive = true }
                launchSingleTop = true
            }
            Toast.makeText(contexto, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
        }
    }

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
                Text(
                    text = "Iniciar Sesión",
                    fontFamily = Pacifico,
                    color = ColorTitulos,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.actualizarEmail(it) },
                    label = { Text(text = "Correo electrónico", fontFamily = LatoLight) },
                    placeholder = { Text(text="email@ejemplo.com", fontFamily = LatoLight) },
                    isError = uiState.error != null,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { viewModel.actualizarPassword(it) },
                    label = { Text(text = "Contraseña", fontFamily = LatoLight) },
                    placeholder = { Text(text ="********", fontFamily = LatoLight) },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = uiState.error != null,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(Modifier.height(20.dp))

                uiState.error?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                    Spacer(Modifier.height(8.dp))
                }

                Button(
                    onClick = { viewModel.Login() },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(45.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorBotonRosa,
                        contentColor = ColorMainBlanco
                    )
                ) {
                    Text("Ingresar", fontFamily = Pacifico, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
                Spacer(Modifier.height(16.dp))

                val annotatedText = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                        append("¿No tienes cuenta? ")
                    }
                    pushStringAnnotation(tag = "REGISTRO", annotation = "registro")
                    withStyle(style = SpanStyle(color = ColorTitulos, fontWeight = FontWeight.Bold)) {
                        append("Regístrate aquí")
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = LatoLight),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "REGISTRO", start = offset, end = offset)
                            .firstOrNull()?.let {
                                navController.navigate("registro")
                            }
                    }
                )
                Spacer(Modifier.height(8.dp))

                Text(
                    text = "o",
                    fontFamily = LatoLight,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Continuar como invitado",
                    fontFamily = LatoLight,
                    color = ColorTitulos,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable {
                        authViewModel.logout()
                        navController.navigate("bienvenida") {
                            popUpTo("login") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
