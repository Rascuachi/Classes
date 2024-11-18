package com.ita.myapp.classes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // Coloca los elementos en una columna, alineados horizontalmente al centro y verticalmente al inicio (parte superior)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Alinea los elementos al principio
    ) {
        // Título pegado al marco superior
        Text(
            text = "This is the HomeScreen",
            modifier = Modifier.padding(top = 16.dp) // Opcional: Añadir un pequeño margen desde el tope
        )

        // Añadir más espacio entre los textos
        Spacer(modifier = Modifier.height(50.dp)) // Este Spacer agrega 50dp de separación

        // Espacio entre botones
        Text(text = "This is the APisScreen")

        // Botón para "APIs" con verde más opaco
        Button(
            onClick = { navController.navigate("apis") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)) // Verde opaco de Android
        ) {
            Text(text = "APIs")
        }

        // Espacio entre botones
        Text(text = "This is the ComponentsScreen")

        // Botón para "Components" con verde más opaco
        Button(
            onClick = { navController.navigate("components") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)) // Verde opaco de Android
        ) {
            Text(text = "Components")
        }
    }
}
