package com.jonesys.proyectopasteleriaandroid.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.jonesys.proyectopasteleriaandroid.ui.components.Footer
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainBeige
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.core.content.ContextCompat
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorMainRosa
import com.jonesys.proyectopasteleriaandroid.ui.theme.ColorTexto
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.jonesys.proyectopasteleriaandroid.R

@SuppressLint("MissingPermission")
@Composable
fun GeolocalizacionScreen(navController: NavHostController) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val scope = rememberCoroutineScope()

    var userLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var locationMessage by remember { mutableStateOf("Buscando ubicación...") }
    var permisosConcedidos by remember { mutableStateOf(false) }
    var showDuocPin by remember { mutableStateOf(false) }

    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            zoom(16.0)
            pitch(30.0)
            bearing(0.0)
        }
    }

    suspend fun recuperarCurrentLocation() {
        try {
            val location = fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY, null
            ).await()
            if (location != null) {
                val lat = location.latitude
                val lon = location.longitude
                userLocation = lat to lon
                mapViewportState.setCameraOptions {
                    center(Point.fromLngLat(lon, lat))
                    zoom(17.0)
                    pitch(30.0)
                    bearing(0.0)
                }
                locationMessage = "Ubicación actual recuperada"
            } else {
                locationMessage = "No se pudo obtener la ubicación"
            }
        } catch (e: Exception) {
            locationMessage = "Error: ${e.message}"
        }
    }

    val locationPermissionLaunch = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permisosConcedidos =
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (permisosConcedidos) {
            locationMessage = "Permisos concedidos"
            scope.launch { recuperarCurrentLocation() }
        } else {
            locationMessage = "Permisos denegados"
        }
    }

    LaunchedEffect(Unit) {
        val hasFine = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasCoarse = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasFine || hasCoarse) {
            permisosConcedidos = true
            locationMessage = "Permisos ya concedidos"
            scope.launch { recuperarCurrentLocation() }
        } else {
            locationPermissionLaunch.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    Scaffold(bottomBar = { Footer(navController) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorMainBeige)
                .padding(innerPadding)
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                MapboxMap(
                    modifier = Modifier.fillMaxSize(),
                    mapViewportState = mapViewportState
                ) {
                    val icon = rememberIconImage(resourceId = R.drawable.ubi)
                    val iconusu = rememberIconImage(resourceId = R.drawable.usuariomapa)

                    // Pin del usuario
                    userLocation?.let { (lat, lon) ->
                        PointAnnotation(
                            point = Point.fromLngLat(lon, lat)
                        ) {
                            iconImage = iconusu
                            iconSize = 0.2
                        }
                    }

                    // Pin del DUOC
                    if (showDuocPin) {
                        PointAnnotation(
                            point = Point.fromLngLat(-70.65759, -33.44731)
                        ) {
                            iconImage = icon
                            iconSize = 0.2
                        }
                    }
                }

            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    if (permisosConcedidos) {
                        val lat = -33.44731
                        val lon = -70.65759
                        userLocation = lat to lon
                        mapViewportState.setCameraOptions {
                            center(Point.fromLngLat(lon, lat))
                            zoom(17.0)
                            pitch(30.0)
                            bearing(0.0)
                        }
                        showDuocPin = true
                        locationMessage =
                            "📍 DUOC UC Padre Alonso de Ovalle\nDirección: Alonso de Ovalle 1586, Santiago"
                    } else {
                        locationPermissionLaunch.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }
                },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.height(40.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorTexto,
                    contentColor = ColorMainRosa
                )
            ) {
                Text(if (permisosConcedidos) "Ir a nuestra ubicación" else "Conceder permisos")
            }

            Spacer(Modifier.height(10.dp))
            Text(locationMessage)
        }
    }
}

