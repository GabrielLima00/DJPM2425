package com.example.taskmanager.repositories

import com.example.taskmanager.models.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

object TaskRepository {
    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    fun addTask(task: Task, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("users").document(userId).collection("tasks")
                .add(task)
                .addOnSuccessListener { documentRef ->
                    val updatedTask = task.copy(docId = documentRef.id)
                    documentRef.set(updatedTask)
                        .addOnSuccessListener {
                            println("Tarefa adicionada e atualizada com docId: ${documentRef.id}")
                            onSuccess()
                        }
                        .addOnFailureListener { e -> onFailure(e) }
                }
                .addOnFailureListener { e -> onFailure(e) }
        } else {
            onFailure(Exception("Utilizador não autenticado"))
        }
    }

    fun getTasksByDate(date: Long, onResult: (List<Task>) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("users").document(userId).collection("tasks")
                .whereEqualTo("date", date)
                .get()
                .addOnSuccessListener { result ->
                    val tasks = result.toObjects(Task::class.java)
                    onResult(tasks)
                }
                .addOnFailureListener {
                    onResult(emptyList())
                }
        } else {
            onResult(emptyList())
        }
    }

    fun getDaysWithTasks(onResult: (List<LocalDate>) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("users").document(userId).collection("tasks")
                .get()
                .addOnSuccessListener { result ->
                    val dates = result.documents.mapNotNull { doc ->
                        val dateInMillis = doc.getLong("date")
                        dateInMillis?.let { millis ->
                            LocalDate.ofEpochDay(millis / 86400000)
                        }
                    }.distinct()
                    onResult(dates)
                }
                .addOnFailureListener { e ->
                    println("Erro ao obter dias com tarefas: ${e.message}")
                    onResult(emptyList())
                }
        } else {
            onResult(emptyList())
        }
    }

    fun removeTask(taskId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("users").document(userId).collection("tasks")
                .document(taskId).delete()
                .addOnSuccessListener {
                    println("Tarefa removida com sucesso!")
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    println("Erro ao remover tarefa: ${e.message}")
                    onFailure(e)
                }
        } else {
            onFailure(Exception("Utilizador não autenticado"))
        }
    }
}

