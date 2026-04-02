package com.example.aprendeaaprender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.aprendeaaprender.ui.LoginScreen
import com.example.aprendeaaprender.ui.SplashScreen
import com.example.aprendeaaprender.ui.WelcomeScreen
import com.example.aprendeaaprender.ui.theme.AprendeaAprenderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AprendeaAprenderTheme {
                var currentScreen by remember { mutableStateOf("splash") }

                when (currentScreen) {
                    "splash" -> SplashScreen(
                        onFinished = { currentScreen = "welcome" }
                    )
                    "welcome" -> WelcomeScreen(
                        onLoginClick = { currentScreen = "login" },
                        onRegisterClick = { currentScreen = "register" }
                    )
                    "login" -> LoginScreen(
                        onLoginClick = { email, password ->
                            // TODO: conectar Firebase Auth
                        },
                        onRegisterClick = { currentScreen = "register" }
                    )
                    "register" -> {
                        // TODO: pantalla de registro
                    }
                }
            }
        }
    }
}