package com.jonesys.proyectopasteleriaandroid.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.jonesys.proyectopasteleriaandroid.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun ScreenWithDrawer(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    showFooter: Boolean = true,
    content: @Composable (PaddingValues) -> Unit
) {
    val isLogged by authViewModel.isLogged.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    val userNombre by authViewModel.userNombre.collectAsState()

    val Pacifico = FontFamily(Font(R.font.pacifico_regular))
    val LatoRegular = FontFamily(Font(R.font.lato_regular))

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                navController = navController,
                drawerState = drawerState,
                scope = scope,
                Pacifico = Pacifico,
                Lato = LatoRegular,
            )
        }
    ) {
        Scaffold(
            containerColor = ColorMainBeige,
            topBar = {
                Header(
                    navController = navController,
                    isLogged = isLogged,
                    userName = userName,
                    userNombre = userNombre,
                    onMenuClick = { scope.launch { drawerState.open() } },
                )
            },
            bottomBar = {
                if (showFooter) {
                    Footer(navController = navController)
                }
            }
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}
