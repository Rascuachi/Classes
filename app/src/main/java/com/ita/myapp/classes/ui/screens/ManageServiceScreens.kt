package com.ita.myapp.classes.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ita.myapp.classes.R
import com.ita.myapp.classes.data.model.ServiceModel
import com.ita.myapp.classes.data.model.controller.ServiceViewModel
import com.ita.myapp.classes.ui.components.TopBar

@Composable
fun ManageServiceScreen(
    navController: NavController,  // Usa el navController recibido como parámetro
    serviceId: String?,
    viewModel: ServiceViewModel = viewModel()
) {
    // Contexto para mensajes
    val context = LocalContext.current
    // Estado inicial del título de la barra y el servicio
    var barTitle by remember { mutableStateOf("Create new service") }
    val serviceState = remember { mutableStateOf(ServiceModel()) }

    // Verificación para actualizar un servicio existente
    if (!serviceId.isNullOrEmpty() && serviceId != "0") {
        barTitle = "Update Service"

        // Carga del servicio
        LaunchedEffect(serviceId) {
            viewModel.showService(serviceId.toInt()) { response ->
                if (response.isSuccessful) {
                    response.body()?.let { service ->
                        serviceState.value = service
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Failed to load service",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // Interfaz gráfica
    Scaffold(
        topBar = { TopBar(barTitle, navController, true) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.dark86))
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Campo de texto: Nombre del servicio
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = serviceState.value.name,
                onValueChange = { serviceState.value = serviceState.value.copy(name = it) },
                label = { Text("Service name") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Campo de texto: Usuario
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = serviceState.value.username,
                onValueChange = { serviceState.value = serviceState.value.copy(username = it) },
                label = { Text("Username") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Campo de texto: Contraseña
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = serviceState.value.password,
                onValueChange = { serviceState.value = serviceState.value.copy(password = it) },
                label = { Text("Password") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Campo de texto: Descripción
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = serviceState.value.description,
                onValueChange = { serviceState.value = serviceState.value.copy(description = it) },
                label = { Text("Description") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botón: Crear o Guardar Cambios
            Button(
                onClick = {
                    val serviceTemp = ServiceModel(
                        name = serviceState.value.name,
                        username = serviceState.value.username,
                        password = serviceState.value.password,
                        description = serviceState.value.description
                    )
                    save(viewModel, context, serviceTemp, serviceId)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,//colorResource(R.color.primary_button),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (serviceId == "0") "CREATE SERVICE" else "SAVE CHANGES")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Botón: Eliminar (si aplica)
            if (!serviceId.isNullOrEmpty() && serviceId.toInt() > 0) {
                OutlinedButton(
                    onClick = {
                        delete(viewModel, context, serviceId, navController)
                    },
                    border = BorderStroke(2.dp, Color.Blue), // Aquí se reemplazó colorResource
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("DELETE")
                }
            }
        }
    }
}
fun save(
    viewModel: ServiceViewModel,
    context: Context,
    service: ServiceModel,
    serviceId: String?
) {
    if (serviceId == "0") {
        viewModel.createService(service) { response ->
            if (response.isSuccessful) {
                Toast.makeText(
                    context,
                    "Service created successfully",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "Error: ${response.body()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    } else if (serviceId != null) {
        viewModel.updateService(serviceId.toInt(), service) { response ->
            if (response.isSuccessful) {
                Toast.makeText(
                    context,
                    "Service updated successfully",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "Error: ${response.body()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
fun delete(
    viewModel: ServiceViewModel,
    context: Context,
    serviceId: String?,
    navController: NavController
) {
    if (serviceId != null && serviceId != "0") {
        viewModel.deleteService(serviceId.toInt()) { response ->
            if (response.isSuccessful) {
                Toast.makeText(
                    context,
                    "Service deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
                navController.popBackStack()
            } else {
                Toast.makeText(
                    context,
                    "Failed to delete service",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
