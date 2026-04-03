package com.example.aprendeaaprender.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.aprendeaaprender.ui.theme.*

@Composable
fun TermsDialog(
    onAccept: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Términos & Condiciones",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CyanAccent
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Para crear tu cuenta, primero debes aceptar los términos y condiciones.",
                    fontSize = 13.sp,
                    color = TextGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Contenido scrolleable
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = """1. Aceptación de los términos
Al registrarse y utilizar la aplicación Aprende a Aprender, el usuario acepta cumplir con los presentes términos y condiciones de uso. Si el usuario no está de acuerdo con alguno de estos términos, deberá abstenerse de utilizar la aplicación.

2. Descripción del servicio
La aplicación Aprende a Aprender es una herramienta digital diseñada para apoyar a estudiantes en la mejora de sus hábitos de estudio, organización académica y estrategias de aprendizaje mediante recomendaciones personalizadas y herramientas de planificación. La aplicación tiene fines educativos y de apoyo académico.

3. Uso adecuado de la aplicación
El usuario se compromete a utilizar la aplicación de manera responsable y ética, respetando los derechos de otros usuarios y las leyes aplicables.

4. Privacidad y protección de datos
La información personal proporcionada será tratada de acuerdo con nuestra política de privacidad. No compartiremos datos personales con terceros sin consentimiento previo.

5. Propiedad intelectual
Todo el contenido de la aplicación, incluyendo textos, gráficos, logos y software, es propiedad de Aprende a Aprender y está protegido por las leyes de propiedad intelectual.""",
                        fontSize = 13.sp,
                        color = TextWhite,
                        lineHeight = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botón Aceptar
                Button(
                    onClick = onAccept,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyanAccent,
                        contentColor = DarkBackground
                    )
                ) {
                    Text(
                        text = "Aceptar",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}