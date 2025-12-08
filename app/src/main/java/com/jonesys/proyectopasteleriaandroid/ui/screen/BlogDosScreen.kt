package com.jonesys.proyectopasteleriaandroid.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.components.Footer
import com.jonesys.proyectopasteleriaandroid.ui.components.Header
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorCard
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel

@Composable
fun BlogDosScreen(navController: NavHostController, authViewModel: AuthViewModel)
{val scrollState = rememberScrollState()
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    val userNombre by authViewModel.userNombre.collectAsState()
    Scaffold(
        containerColor = ColorMainBeige,
        topBar = { Header(navController = navController, isLogged = isLogged, userName = userName, userNombre = userNombre) },
        bottomBar = { Footer(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorMainBeige)
                .padding(innerPadding)
                .statusBarsPadding()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = ColorCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Un hito historico en la pasteleria chilena",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "En el corazón de una pequeña ciudad del sur de Chile, cientos de pasteleros, estudiantes y voluntarios se unieron para un desafío que parecía imposible: preparar la torta más grande del país. Desde muy temprano, las calles se llenaron de aromas dulces mientras enormes bandejas de bizcocho salían de los hornos comunitarios.",
                        fontSize = 16.sp,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(13.dp))
                    Text("El secreto del éxito no fue solo la receta, sino la coordinación y el trabajo en equipo. Cada capa debía colocarse con precisión, y cada kilo de crema batida era aplicado como si se tratara de una obra de arte. El pueblo entero colaboró: unos aportaban ingredientes, otros decoraban, y muchos más ayudaban a mantener la organización.",
                        fontSize = 16.sp,
                        color = ColorTexto
                    )
                    Image(
                        painter = painterResource(id = R.drawable.record),
                        contentDescription = "Ingredientes",
                        modifier = Modifier.size(345.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text("Finalmente, tras horas de esfuerzo, la torta quedó lista: una creación monumental que rompió el récord nacional. No solo fue un logro gastronómico, sino también un símbolo de unión y orgullo colectivo."
                        ,fontSize = 16.sp,
                        color = ColorTexto
                    )

                }
            }
        }
    }
}