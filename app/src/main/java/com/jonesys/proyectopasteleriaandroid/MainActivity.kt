package com.jonesys.proyectopasteleriaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jonesys.proyectopasteleriaandroid.ui.screen.Fondo
import com.jonesys.proyectopasteleriaandroid.ui.screen.LoginScreen
import com.jonesys.proyectopasteleriaandroid.ui.theme.ProyectoPasteleriaAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            ProyectoPasteleriaAndroidTheme {
            }
        }
    }
}
