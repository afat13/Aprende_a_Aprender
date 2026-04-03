package com.example.aprendeaaprender.ui

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthManager {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance(
        "https://backend-34179-default-rtdb.firebaseio.com/"
    )

    fun registrarUsuario(
        nombres: String,
        apellidos: String,
        correo: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(correo, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: return@addOnSuccessListener

                val usuario = mapOf(
                    "nombres" to nombres,
                    "apellidos" to apellidos,
                    "correo" to correo
                )

                database.reference
                    .child("usuarios")
                    .child(uid)
                    .setValue(usuario)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onError(it.message ?: "Error al guardar datos") }
            }
            .addOnFailureListener { onError(it.message ?: "Error al registrar") }
    }

    fun iniciarSesion(
        correo: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(correo, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Error al iniciar sesión") }
    }

    fun cerrarSesion() {
        auth.signOut()
    }

    fun usuarioActual() = auth.currentUser
}