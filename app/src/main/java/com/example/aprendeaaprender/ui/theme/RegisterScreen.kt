package com.example.aprendeaaprender.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aprendeaaprender.R
import com.example.aprendeaaprender.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterClick: (String, String, String, String) -> Unit = { _, _, _, _ -> },
    onTermsClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }
    var showTermsDialog by remember { mutableStateOf(false) }

    if (showTermsDialog) {
        TermsDialog(
            onAccept = {
                acceptedTerms = true
                showTermsDialog = false
            },
            onDismiss = { showTermsDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Flecha de regreso
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        ) {
            Text(
                text = "‹",
                fontSize = 32.sp,
                color = CyanAccent,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo y título
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(90.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Aprende a Aprender",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = CyanAccent,
                    textAlign = TextAlign.Center
                )
            }

            // Campos de texto
            Column {
                RegisterField(value = nombres, label = "Nombres", onValueChange = { nombres = it })
                Spacer(modifier = Modifier.height(8.dp))
                RegisterField(value = apellidos, label = "Apellidos", onValueChange = { apellidos = it })
                Spacer(modifier = Modifier.height(8.dp))
                RegisterField(value = correo, label = "Correo", onValueChange = { correo = it })
                Spacer(modifier = Modifier.height(8.dp))
                RegisterField(value = password, label = "Contraseña", onValueChange = { password = it }, isPassword = true)
                Spacer(modifier = Modifier.height(8.dp))
                RegisterField(value = confirmPassword, label = "Confirma tu contraseña", onValueChange = { confirmPassword = it }, isPassword = true)
            }

            // Checkbox y botón
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = acceptedTerms,
                        onCheckedChange = { acceptedTerms = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = CyanAccent,
                            uncheckedColor = TextGray,
                            checkmarkColor = DarkBackground
                        )
                    )
                    Text(text = "Acepto los ", color = TextGray, fontSize = 12.sp)
                    Text(
                        text = "términos y condiciones",
                        color = CyanAccent,
                        fontSize = 12.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { showTermsDialog = true }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (acceptedTerms && password == confirmPassword) {
                            onRegisterClick(nombres, apellidos, correo, password)
                        }
                    },
                    enabled = acceptedTerms && password.isNotEmpty() && password == confirmPassword,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyanAccent,
                        contentColor = DarkBackground,
                        disabledContainerColor = CyanAccent.copy(alpha = 0.4f)
                    )
                ) {
                    Text(
                        text = "Regístrese",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 13.sp) },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        textStyle = TextStyle(fontSize = 14.sp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = CyanAccent,
            unfocusedBorderColor = TextGray,
            focusedLabelColor = CyanAccent,
            cursorColor = CyanAccent,
            focusedTextColor = TextWhite,
            unfocusedTextColor = TextWhite
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    AprendeaAprenderTheme {
        RegisterScreen()
    }
}