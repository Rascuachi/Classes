package com.ita.myapp.classes

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.ita.myapp.classes.data.model.database.AppDatabase
import com.ita.myapp.classes.data.model.database.DatabaseProvider
import com.ita.myapp.classes.ui.screens.APisScreen
import com.ita.myapp.classes.ui.screens.HomeScreen
import com.ita.myapp.classes.ui.screens.ComponentsScreen
import com.ita.myapp.classes.ui.screens.LoginScreen
import com.ita.myapp.classes.ui.biometrics.BiometricsScreen
import com.ita.myapp.classes.ui.location.MapsSearchView
import com.ita.myapp.classes.ui.screens.CameraScreen
import com.ita.myapp.classes.ui.screens.ManageServiceScreen

class MainActivity : AppCompatActivity() {
    lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            database = DatabaseProvider.getDatabase(this)
            Log.d("DB","Database loaded succesfully")
        }catch(exception:Exception){
            Log.d("DB","error:$exception")
        }
        enableEdgeToEdge()
        // Establecer contenido usando Jetpack Compose
        setContent {
            ProvideWindowInsets {
                ComposeMultiScreenApp(this)
            }
        }
    }
}

@Composable
fun ComposeMultiScreenApp(activity: AppCompatActivity) {
    val navController = rememberNavController()
    Surface(color = Color.White) {
        SetupNavGraph(navController = navController, activity = activity)
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController, activity: AppCompatActivity) {
    NavHost(navController = navController, startDestination = "biometrics") {
        composable("home") { HomeScreen(navController) }
        composable("components") { ComponentsScreen(navController) }
        composable("biometrics") { BiometricsScreen(navController = navController, activity = activity) }
        composable("login") { LoginScreen(navController) }
        composable("apis") { APisScreen(navController) }
        composable("MapsSearchView/{lat}/{long}/{address}") { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDouble() ?: 0.0
            val long = backStackEntry.arguments?.getString("long")?.toDouble() ?: 0.0  // Convert to Double here
            val address = backStackEntry.arguments?.getString("address") ?: ""
            MapsSearchView(lat, long, address)
        }
        composable("CameraScreen") {
            CameraScreen(context = LocalContext.current)
        }
        composable("manage-service/{serviceId}"){backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")
            ManageServiceScreen(navController, serviceId = serviceId)

        }
    }
}
