package com.jonesys.proyectopasteleriaandroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainRosa
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto

@Composable
fun Header(
    navController: NavHostController,
    isLogged: Boolean = false,
    userName: String? = null,
    userNombre: String? = null
) {
    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoRegular = FontFamily(Font(R.font.lato_regular))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorMainRosa)
            .padding(horizontal = 16.dp, vertical = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(44.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Pastelería mil sabores",
                fontSize = 18.sp,
                fontFamily = Pacifico,
                color = ColorTexto,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (isLogged) {
            Text(
                text = "Bienvenid@ ${userNombre ?: userName ?: ""}",
                fontSize = 14.sp,
                fontFamily = LatoRegular,
                fontWeight = FontWeight.Medium,
                color = ColorTexto
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.height(36.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorTexto,
                        contentColor = ColorMainRosa
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Iniciar", fontSize = 13.sp, fontFamily = LatoRegular)
                }
                Button(
                    onClick = { navController.navigate("registro") },
                    modifier = Modifier.height(36.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorTexto,
                        contentColor = ColorMainRosa
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Registrarse", fontSize = 13.sp, fontFamily = LatoRegular)
                }
            }
        }
    }
}
