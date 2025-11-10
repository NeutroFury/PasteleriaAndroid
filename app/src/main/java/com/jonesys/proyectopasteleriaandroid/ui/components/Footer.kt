package com.jonesys.proyectopasteleriaandroid.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBlanco
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
                .size(40.dp)
                .clip(RoundedCornerShape(percent = 50))
                .background(ColorMainBlanco.copy(alpha = 0.08f), RoundedCornerShape(percent = 50))
                .clickable { navController.navigate("bienvenida") }
                .padding(6.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.ubicacion),
            contentDescription = "Ubicacion",
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(percent = 50))
                .background(ColorMainBlanco.copy(alpha = 0.08f), RoundedCornerShape(percent = 50))
                .clickable { navController.navigate("geo") }
                .padding(6.dp),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = "Productos",
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(percent = 50))
                .background(ColorMainBlanco.copy(alpha = 0.08f), RoundedCornerShape(percent = 50))
                .clickable { navController.navigate("productos") }
                .padding(6.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.usuario),
            contentDescription = "Perfil",
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(percent = 50))
                .background(ColorMainBlanco.copy(alpha = 0.08f), RoundedCornerShape(percent = 50))
                .clickable { navController.navigate("perfil") }
                .padding(6.dp),
            contentScale = ContentScale.Crop
        )
    }
}