package com.ita.myapp.classes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ita.myapp.classes.ui.components.ServiceDetailCard

import androidx.compose.runtime.*
import androidx.compose.material3.*

import com.ita.myapp.classes.ui.components.ServiceCard
import com.ita.myapp.classes.ui.components.ServiceDetailCard

@Composable
fun HomeScreen(navController: NavController, viewModel: ServiceViewModel = viewModel()) {
    var serviceDetail by remember { mutableStateOf<ServiceModel?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var showButtonSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar("PasswordManager", navController, false) },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                content = { /* BottomBar Content */ }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colorResource(R.color.primary_button),
                onClick = { navController.navigate("manage-service/0") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        // Mostrar CircularProgressIndicator si no se han cargado los servicios
        if (services.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.padding(innerPadding))
        }

        // Obtener los servicios
        LaunchedEffect(Unit) {
            viewModel.getServices { response ->
                if (response.isSuccessful) {
                    services = response.body() ?: emptyList()
                } else {
                    println("Failed to load services")
                }
            }
        }

        // Mostrar la lista de servicios
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(R.color.dark86))
                .fillMaxSize()
        ) {
            items(services) { service ->
                ServiceCard(
                    service.id, service.name, service.username, service.imageURL,
                    onButtonClick = {
                        viewModel.showService(service.id) { response ->
                            if (response.isSuccessful) {
                                serviceDetail = response.body()
                            }
                        }
                        showButtonSheet = true
                    }
                )
            }
        }

        // Mostrar el ModalBottomSheet si es necesario
        if (showButtonSheet) {
            ModalBottomSheet(
                containerColor = colorResource(R.color.borderCard),
                contentColor = Color.White,
                modifier = Modifier.fillMaxHeight(),
                onDismissRequest = { showButtonSheet = false },
                sheetState = sheetState
            ) {
                ServiceDetailCard(
                    idServiceDetail = serviceDetail?.id ?: 0,
                    serviceDetail?.name ?: "",
                    serviceDetail?.username ?: "",
                    serviceDetail?.password ?: "",
                    serviceDetail?.imageURL,
                    onEditClick = {
                        showButtonSheet = false
                        navController.navigate("manage-service/${serviceDetail?.id}")
                    }
                )
            }
        }

        // Contenido adicional en la pantalla
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "This is the HomeScreen",
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(text = "This is the APisScreen")

            Button(
                onClick = { navController.navigate("apis") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
            ) {
                Text(text = "APIs")
            }

            Text(text = "This is the ComponentsScreen")

            Button(
                onClick = { navController.navigate("components") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
            ) {
                Text(text = "Components")
            }
        }
    }
}
