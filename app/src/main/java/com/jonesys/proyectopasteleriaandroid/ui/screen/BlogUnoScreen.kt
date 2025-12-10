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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.ui.components.ScreenWithDrawer
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorCard
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel

@Composable
fun BlogUnoScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val scrollState = rememberScrollState()

    ScreenWithDrawer(
        navController = navController,
        authViewModel = authViewModel
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Aquellos ingredientes que nos definen",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "La repostería chilena es conocida por su sabor único y delicioso, que se debe en gran parte a los ingredientes típicos que se utilizan en sus recetas. Algunos de los ingredientes más comunes en la repostería chilena incluyen:",
                        fontSize = 16.sp,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(13.dp))
                    Text(
                        "Manjar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                    Text(
                        "El manjar es una especie de dulce de leche que se utiliza en muchas recetas de repostería chilena, como la torta de mil hojas y los alfajores.",
                        fontSize = 16.sp,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(13.dp))
                    Text(
                        "Chirimoya",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                    Text(
                        "La chirimoya es una fruta tropical que se utiliza en postres como la chirimoya rellena y la torta de chirimoya.",
                        fontSize = 16.sp,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(13.dp))
                    Text(
                        "Nueces",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                    Text(
                        "Las nueces son un ingrediente común en la repostería chilena, especialmente en la torta de nueces y los alfajores.",
                        fontSize = 16.sp,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(13.dp))
                    Text(
                        "Frutas secas",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                    Text(
                        "Las frutas secas como las pasas y los higos se utilizan en muchas recetas de repostería chilena, como la torta de pasas y nueces.",
                        fontSize = 16.sp,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(13.dp))
                    Text(
                        "Harina de maíz",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                    Text(
                        "La harina de maíz se utiliza en algunas recetas de repostería chilena, como el mote con huesillo y la torta de choclo.",
                        fontSize = 16.sp,
                        color = ColorTexto
                    )
                    Spacer(Modifier.height(15.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ingredientes),
                        contentDescription = "Ingredientes",
                        modifier = Modifier.size(300.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(Modifier.height(15.dp))
                    Text(
                        "En resumen, la repostería chilena utiliza una variedad de ingredientes típicos que le dan su sabor único y delicioso. Desde el manjar hasta las frutas secas, estos ingredientes son esenciales para crear los postres tradicionales que tanto gustan en Chile.",
                        fontSize = 16.sp,
                        color = ColorTexto
                    )
                }
            }
        }
    }
}
