package com.example.aprendeaaprender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.aprendeaaprender.ui.*
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
                            // TODO: Firebase Auth login
                        },
                        onRegisterClick = { currentScreen = "register" },
                        onBackClick = { currentScreen = "welcome" }
                    )
                    "register" -> RegisterScreen(
                        onRegisterClick = { nombres, apellidos, correo, password ->
                            // TODO: Firebase Auth crear cuenta
                            currentScreen = "verify"
                        },
                        onTermsClick = { },
                        onBackClick = { currentScreen = "welcome" }
                    )
                    "verify" -> VerifyEmailScreen(
                        onValidateClick = { code ->
                            // TODO: verificar código
                            currentScreen = "verified"
                        },
                        onResendClick = {
                            // TODO: reenviar código
                        },
                        onBackClick = { currentScreen = "register" }
                    )
                    "verified" -> VerifiedScreen(
                        onLoginClick = { currentScreen = "login" }
                    )
                }
            }
        }
    }
}