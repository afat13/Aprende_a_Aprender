package com.example.aprendeaaprender.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aprendeaaprender.ui.theme.*

@Composable
fun VerifyEmailScreen(
    email: String = "correo@ejemplo.com",
    onValidateClick: (String) -> Unit = {},
    onResendClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var code by remember { mutableStateOf("") }
    var showResendDialog by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    // Diálogo de código reenviado
    if (showResendDialog) {
        AlertDialog(
            onDismissRequest = { showResendDialog = false },
            containerColor = DarkSurface,
            title = {
                Text(
                    text = "Código Reenviado",
                    color = CyanAccent,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Te hemos enviado un nuevo código a tu correo. Podría tomar unos segundos en llegar.",
                    color = TextWhite,
                    fontSize = 14.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { showResendDialog = false }) {
                    Text("Aceptar", color = CyanAccent)
                }
            }
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
            modifier = Modifier.padding(start = 8.dp, top = 16.dp)
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Código de verificación.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = CyanAccent
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Digita el código enviado a tu dirección de correo registrada.",
                fontSize = 14.sp,
                color = TextGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Campos de código (6 dígitos)
            BasicTextField(
                value = code,
                onValueChange = { if (it.length <= 6) code = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.focusRequester(focusRequester),
                decorationBox = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        repeat(6) { index ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp)
                                    .border(
                                        width = 2.dp,
                                        color = if (index < code.length) CyanAccent else TextGray,
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (index < code.length) code[index].toString() else "",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextWhite
                                )
                            }
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Reenviar código
            Text(
                text = "Reenviar código",
                color = CyanLight,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    onResendClick()
                    showResendDialog = true
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón Validar
            Button(
                onClick = { onValidateClick(code) },
                enabled = code.length == 6,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CyanAccent,
                    contentColor = DarkBackground,
                    disabledContainerColor = CyanAccent.copy(alpha = 0.4f)
                )
            ) {
                Text(
                    text = "Validar Correo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VerifyEmailScreenPreview() {
    AprendeaAprenderTheme {
        VerifyEmailScreen()
    }
}