package com.ita.myapp.classes.ui.biometrics

import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BiometricsScreen(navController: NavController, activity: AppCompatActivity) {
    val promptManager by lazy {
        BiometricPromptManager(activity)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        // Access the flow to collect events
        val biometricResult by promptManager.promptResults.collectAsState(initial = null)

        // In order to fire and launch the activity to set a biometric or enroll it
        val enrollLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                println("Activity result: $it")
            }
        )

        // To prompt the user to set a biometric signature in case it hasn't been set
        LaunchedEffect(biometricResult) {
            if (biometricResult is BiometricPromptManager.BiometricResult.AuthenticationNotSet) {
                if (Build.VERSION.SDK_INT >= 30) {
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(
                            Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                        )
                    }
                    // Fire the activity
                    enrollLauncher.launch(enrollIntent)
                }
            }
        }

        // Automatically trigger biometric authentication on screen load
        LaunchedEffect(Unit) {
            promptManager.showBiometricPrompt(
                title = "Sample prompt",
                description = "Sample prompt description"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            biometricResult?.let { result ->
                // Handle biometric result and display feedback
                Text(
                    text = when (result) {
                        is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                            result.error
                        }
                        BiometricPromptManager.BiometricResult.AuthenticationFailed -> {
                            "Authentication failed"
                        }
                        BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                            "Authentication not set"
                        }
                        BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                            // On successful authentication, navigate to HomeScreen
                            // Redirect to HomeScreen after successful authentication
                            navController.navigate("home")
                            "Authentication success"
                        }
                        BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                            "Feature unavailable"
                        }
                        BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                            "Hardware unavailable"
                        }
                    }
                )
            }
        }
    }
}
