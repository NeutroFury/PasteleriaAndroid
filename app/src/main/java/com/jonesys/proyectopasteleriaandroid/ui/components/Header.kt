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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
    userNombre: String? = null,
    onMenuClick: () -> Unit = {}
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
        IconButton(
            onClick = onMenuClick,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menú",
                tint = ColorTexto
            )
        }

        Spacer(Modifier.width(180.dp))

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
                    modifier = Modifier.height(40.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorTexto,
                        contentColor = ColorMainRosa
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Iniciar", fontSize = 14.sp, fontFamily = LatoRegular)
                }
                Button(
                    onClick = { navController.navigate("registro") },
                    modifier = Modifier.height(40.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorTexto,
                        contentColor = ColorMainRosa
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Registrarse", fontSize = 14.sp, fontFamily = LatoRegular)
                }
            }
        }
    }
}
