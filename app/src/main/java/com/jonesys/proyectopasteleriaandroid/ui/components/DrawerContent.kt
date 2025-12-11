package com.jonesys.proyectopasteleriaandroid.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    Pacifico: FontFamily,
    Lato: FontFamily
) {
    ModalDrawerSheet(
        drawerContainerColor = ColorMainBeige,
        modifier = Modifier.width(280.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Menú",
                fontFamily = Pacifico,
                fontSize = 28.sp,
                color = ColorTitulos,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            HorizontalDivider(color = ColorTexto.copy(alpha = 0.3f), thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            DrawerMenuItem(
                text = "Tienda Online",
                icon = Icons.Default.ShoppingCart,
                onClick = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("productos")
                    }
                },
                Lato = Lato
            )

            Text(
                text = "Blogs",
                fontFamily = Pacifico,
                fontSize = 20.sp,
                color = ColorTitulos,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
            )

            DrawerMenuItem(
                text = "Ingredientes Emblemáticos",
                icon = Icons.Default.Star,
                onClick = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("blogUno")
                    }
                },
                Lato = Lato
            )

            DrawerMenuItem(
                text = "Historia de la Pastelería",
                icon = Icons.Default.Info,
                onClick = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("blogDos")
                    }
                },
                Lato = Lato
            )

            Spacer(modifier = Modifier.height(8.dp))


            DrawerMenuItem(
                text = "Postres Internacionales",
                icon = Icons.Default.Cake,
                onClick = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("postres_externos")
                    }
                },
                Lato = Lato
            )

            Spacer(modifier = Modifier.height(8.dp))

            DrawerMenuItem(
                text = "Ofertas Especiales",
                icon = Icons.Default.Sell,
                onClick = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate("carrito")
                    }
                },
                Lato = Lato
            )
        }
    }
}

@Composable
private fun DrawerMenuItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    Lato: FontFamily
) {
    NavigationDrawerItem(
        label = {
            Text(
                text = text,
                fontFamily = Lato,
                fontSize = 16.sp
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = text
            )
        },
        selected = false,
        onClick = onClick,
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor = ColorMainBeige,
            unselectedTextColor = ColorTexto,
            unselectedIconColor = ColorTexto,
            selectedContainerColor = ColorMainRosa.copy(alpha = 0.2f),
            selectedTextColor = ColorTitulos,
            selectedIconColor = ColorTitulos
        ),
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
