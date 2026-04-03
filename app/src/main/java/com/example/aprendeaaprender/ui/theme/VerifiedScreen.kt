package com.example.aprendeaaprender.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aprendeaaprender.ui.theme.*

@Composable
fun VerifiedScreen(
    onLoginClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ícono de éxito
        Text(
            text = "✓",
            fontSize = 64.sp,
            color = CyanAccent,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¡Cuenta verificada!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = CyanAccent
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tu identidad ha sido confirmada con éxito.\nYa puedes disfrutar de todas las funciones\nde Aprende a Aprender.",
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CyanAccent,
                contentColor = DarkBackground
            )
        ) {
            Text(
                text = "Inicie Sesión",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VerifiedScreenPreview() {
    AprendeaAprenderTheme {
        VerifiedScreen()
    }
}