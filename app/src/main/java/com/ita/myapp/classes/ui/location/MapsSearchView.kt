package com.ita.myapp.classes.ui.location

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapsSearchView(lat: Double, long: Double, address: String) {
    // Verificar si las coordenadas son válidas
    Log.d("MapsSearchView", "Recibiendo datos - Lat: $lat, Long: $long, Address: $address")

    if (lat == 0.0 || long == 0.0) {
        Log.e("MapsSearchView", "Coordenadas inválidas: Lat: $lat, Long: $long")
        return
    }

    if (address.isEmpty()) {
        Log.e("MapsSearchView", "Dirección vacía recibida: $address")
        return
    }

    val place = LatLng(lat, long) // Coordenadas de latitud y longitud
    val markerState = rememberMarkerState(position = place)
    val cameraPosition = CameraPosition.fromLatLngZoom(place, 12f) // Zoom ajustado
    val cameraState = rememberCameraPositionState {
        position = cameraPosition
    }

    // Verificación adicional en el log de la posición y dirección antes de mostrar el mapa
    Log.d("MapsSearchView", "Coordenadas válidas: Lat: $lat, Long: $long, Dirección: $address")

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraState
        ) {
            // Añadir un marcador en la ubicación especificada
            Marker(
                state = markerState,
                title = "Ubicación",
                snippet = address,
                onInfoWindowClick = {
                    // Acción cuando se hace clic en la ventana de información del marcador
                    Log.d("MapsSearchView", "Info window clicked: $address")
                }
            )
        }

        // Mostrar el CardMarker en la parte superior
        CardMarker(address = address)
    }
}

@Composable
fun CardMarker(address: String) {
    Card(
        modifier = Modifier
            .padding(18.dp)
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Ubicación",
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = address,
                modifier = Modifier.padding(all = 15.dp),
                fontSize = 20.sp
            )
        }
    }
}
