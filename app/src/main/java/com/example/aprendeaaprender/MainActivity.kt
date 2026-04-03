package com.example.aprendeaaprender

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.aprendeaaprender.ui.*
import com.example.aprendeaaprender.ui.theme.AprendeaAprenderTheme

class MainActivity : ComponentActivity() {

    private val authManager = AuthManager()

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
                            authManager.iniciarSesion(
                                correo = email,
                                password = password,
                                onSuccess = {
                                    Toast.makeText(this@MainActivity, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
                                    // TODO: navegar al menú principal
                                },
                                onError = { error ->
                                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                                }
                            )
                        },
                        onRegisterClick = { currentScreen = "register" },
                        onBackClick = { currentScreen = "welcome" }
                    )
                    "register" -> RegisterScreen(
                        onRegisterClick = { nombres, apellidos, correo, password ->
                            authManager.registrarUsuario(
                                nombres = nombres,
                                apellidos = apellidos,
                                correo = correo,
                                password = password,
                                onSuccess = {
                                    Toast.makeText(this@MainActivity, "Cuenta creada", Toast.LENGTH_SHORT).show()
                                    currentScreen = "verify"
                                },
                                onError = { error ->
                                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                                }
                            )
                        },
                        onTermsClick = { },
                        onBackClick = { currentScreen = "welcome" }
                    )
                    "verify" -> VerifyEmailScreen(
                        onValidateClick = { code ->
                            currentScreen = "verified"
                        },
                        onResendClick = { },
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