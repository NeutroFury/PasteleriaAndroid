package com.jonesys.proyectopasteleriaandroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainRosa
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto

@Composable
fun Header(){
    Row(modifier = Modifier
        .padding(vertical = 20.dp, horizontal = 20.dp)
        .fillMaxWidth()
        .background(ColorMainRosa),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(48.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Fit

        )
    }

    Text("Pasteleria mil sabores",
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = ColorTexto
        )
    }
    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorMainRosa,
            contentColor = ColorTexto
        ),
        shape = RoundedCornerShape(16.dp)
    )
    {Text("Iniciar sesión")
    }
}
