package com.example.taskmanager.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    val currentUser get() = auth.currentUser

    fun signUp(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                println("Utilizador registado com sucesso: ${auth.currentUser?.uid}")
                onSuccess()
            }
            .addOnFailureListener { e ->
                println("Erro ao registar: ${e.message}")
                onFailure(e)
            }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                println("Login efetuado: ${auth.currentUser?.uid}")
                onSuccess()
            }
            .addOnFailureListener { e ->
                println("Erro no login: ${e.message}")
                onFailure(e)
            }
    }

    fun logout() {
        auth.signOut()
    }
}