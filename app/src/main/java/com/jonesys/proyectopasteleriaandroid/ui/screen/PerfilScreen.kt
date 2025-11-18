package com.jonesys.proyectopasteleriaandroid.ui.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PerfilScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoLight = FontFamily(Font(R.font.lato_light))
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

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
                colors = CardDefaults.cardColors(containerColor = ColorMainBlanco),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        singleLine = true,
                        placeholder = { Text("Pedrito", fontFamily = LatoLight) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = textFieldColors()
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        singleLine = true,
                        placeholder = { Text("pedroelbkn@gmail.com", fontFamily = LatoLight) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = textFieldColors()
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        singleLine = true,
                        placeholder = { Text("+5694206967", fontFamily = LatoLight) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = textFieldColors()
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        singleLine = true,
                        placeholder = { Text("********", fontFamily = LatoLight) },
                        visualTransformation = PasswordVisualTransformation(),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = textFieldColors()
                    )

                    Spacer(Modifier.height(8.dp))
                    Padre(onClear = {
                        username = ""
                        email = ""
                        phone = ""
                        password = ""
                    })
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun textFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = ColorCard,
    unfocusedContainerColor = ColorCard,
    disabledContainerColor = ColorCard,
    focusedBorderColor = ColorTexto.copy(alpha = 0.45f),
    unfocusedBorderColor = ColorTexto.copy(alpha = 0.25f),
    cursorColor = ColorTexto,
    focusedTextColor = ColorTexto,
    unfocusedTextColor = ColorTexto,
    focusedLabelColor = ColorTexto,
    unfocusedLabelColor = ColorTexto,
    focusedPlaceholderColor = ColorTexto.copy(alpha = 0.5f),
    unfocusedPlaceholderColor = ColorTexto.copy(alpha = 0.5f)
)

@Composable
fun Padre(onClear: () -> Unit){
    // declarar la variable Booleana (cambiara estado)
    var estadoVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // al hacer click se alterna estadoVisible y luego se ejecuta onClear (después del delay en BotonCargando)
        BotonCargando(
            onClick = {
                estadoVisible = !estadoVisible
                onClear()
            }
        )

    }
}
@Composable
fun BotonCargando(onClick: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var cargando by remember { mutableStateOf(false) }

    Button(
        onClick = {
            cargando = true
            scope.launch {
                delay(2000)
                cargando = false
                Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT).show()
                onClick()
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
        if (cargando) {
            CircularProgressIndicator(
                color = ColorTexto,
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            Text("Actualizar Perfil")
        }
    }
}
