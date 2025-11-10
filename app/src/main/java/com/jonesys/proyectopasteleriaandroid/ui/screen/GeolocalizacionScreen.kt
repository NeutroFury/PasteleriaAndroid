// kotlin
package com.jonesys.proyectopasteleriaandroid.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@SuppressLint("MissingPermission")
@Composable
fun GeolocalizacionScreen(navController: NavHostController) {
    val contexto = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(contexto) }
    val scope = rememberCoroutineScope()

    var userLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var locationMessage by remember { mutableStateOf("Buscando Ubicacion...") }
    var permisosConcedidos by remember { mutableStateOf(false) }

    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            zoom(18.0)
            pitch(0.0)
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
                    zoom(18.0)
                }
                locationMessage = "Ubicacion Recuperada"
            } else {
                locationMessage = "No se pudo obtener la ubicacion"
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
        locationMessage = if (permisosConcedidos) "Permisos Concedidos" else "Permisos Denegados"
        if (permisosConcedidos) {
            scope.launch { recuperarCurrentLocation() }
        }
    }

    LaunchedEffect(Unit) {
        locationPermissionLaunch.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
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
            ) {
                MapboxMap(
                    modifier = Modifier.fillMaxSize(),
                    mapViewportState = mapViewportState
                )
            }

            Button(
                onClick = {
                    if (permisosConcedidos) {
                        val lat = -33.44731
                        val lon = -70.65759

                        userLocation = lat to lon
                        mapViewportState.setCameraOptions {
                            center(Point.fromLngLat(lon, lat))
                            zoom(18.0)
                            pitch(0.0)
                            bearing(0.0)
                        }
                        locationMessage = "Dirección: Padre Alonso de Ovalle 1586, 8330196 Santiago, Región Metropolitana"
                    } else {
                        locationPermissionLaunch.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (permisosConcedidos) "Actualizar Ubicación" else "Conceder Permisos")
            }

            Spacer(Modifier.height(10.dp))
            Text(locationMessage)
        }
    }
}
