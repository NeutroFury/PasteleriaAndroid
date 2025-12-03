package com.jonesys.proyectopasteleriaandroid.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.components.Footer
import com.jonesys.proyectopasteleriaandroid.ui.components.Header
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorBotonRosa
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorCard
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBlanco
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainRosa
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTitulos
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import com.jonesys.proyectopasteleriaandroid.viewmodel.PerfilViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PerfilScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    perfilViewModel: PerfilViewModel = viewModel()
) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    val usuario by perfilViewModel.usuario.collectAsState()
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoRegular = FontFamily(Font(R.font.lato_regular))

    Scaffold(
        containerColor = ColorMainBeige,
        topBar = { Header(navController = navController, isLogged = isLogged, userName = userName) },
        bottomBar = { Footer(navController = navController) }
    ) { innerPadding ->
        val scroll = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorMainBeige)
                .padding(innerPadding)
                .statusBarsPadding()
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(ColorMainRosa.copy(alpha = 0.12f))
                )
                Image(
                    painter = painterResource(id = R.drawable.monke),
                    contentDescription = "Usuario",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(96.dp)
                        .clip(CircleShape)
                        .border(3.dp, ColorCard, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = "Cambiar foto",
                fontSize = 13.sp,
                color = ColorTexto.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 6.dp, bottom = 12.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Editar Perfil",
                        fontFamily = Pacifico,
                        color = ColorTitulos,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = usuario.nombre,
                        onValueChange = perfilViewModel::onChangeNombre,
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

                    OutlinedTextField(
                        value = usuario.email,
                        onValueChange = perfilViewModel::onChangeEmail,
                        label = { Text("Correo electrónico", fontFamily = LatoRegular) },
                        placeholder = { Text("email@ejemplo.com", fontFamily = LatoRegular) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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

                    OutlinedTextField(
                        value = usuario.telefono,
                        onValueChange = perfilViewModel::onChangeTelefono,
                        label = { Text("Teléfono", fontFamily = LatoRegular) },
                        placeholder = { Text("+56912345678", fontFamily = LatoRegular) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        isError = usuario.error.telefono != null,
                        supportingText = {
                            usuario.error.telefono?.let {
                                Text(it, color = MaterialTheme.colorScheme.error, fontFamily = LatoRegular)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = usuario.password,
                        onValueChange = perfilViewModel::onChangePassword,
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

                    Spacer(Modifier.height(20.dp))

                    BotonActualizar(perfilViewModel = perfilViewModel)
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun BotonActualizar(perfilViewModel: PerfilViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var cargando by remember { mutableStateOf(false) }
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoRegular = FontFamily(Font(R.font.lato_regular))

    Button(
        onClick = {
            if (perfilViewModel.validar()) {
                cargando = true
                scope.launch {
                    delay(2000)
                    cargando = false
                    Toast.makeText(context, "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Verifique los campos", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(45.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorBotonRosa,
            contentColor = ColorMainBlanco
        ),
        enabled = !cargando
    ) {
        if (cargando) {
            CircularProgressIndicator(
                color = ColorMainBlanco,
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            Text(
                "Actualizar",
                fontFamily = LatoRegular,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
