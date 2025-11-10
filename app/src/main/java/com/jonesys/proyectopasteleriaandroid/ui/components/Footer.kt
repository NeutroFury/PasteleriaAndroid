package com.jonesys.proyectopasteleriaandroid.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainRosa


@Composable
fun Footer(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorMainRosa)
            .padding(horizontal = 16.dp, vertical = 30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.inicio),
            contentDescription = "Inicio",
            modifier = Modifier
                .size(35.dp)
                .clickable { navController.navigate("bienvenida") },
            contentScale = ContentScale.Fit
        )
        Image(
            painter = painterResource(id = R.drawable.ubicacion),
            contentDescription = "Ubicacion",
            modifier = Modifier
                .size(35.dp)
                .clickable { navController.navigate("geo") },
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = "Productos",
            modifier = Modifier
                .size(35.dp)
                .clickable { navController.navigate("productos") },
            contentScale = ContentScale.Fit
        )
        Image(
            painter = painterResource(id = R.drawable.usuario),
            contentDescription = "Perfil",
            modifier = Modifier
                .size(35.dp)
                .clickable { navController.navigate("perfil") },
            contentScale = ContentScale.Fit
        )
    }
}