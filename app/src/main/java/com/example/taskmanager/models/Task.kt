package com.example.taskmanager.models

import java.time.LocalDate

data class Task(
    val title: String = "",
    val description: String = "",
    val date: Long = LocalDate.now().toEpochDay() * 86400000,
    val docId: String? = null,
    var notificationScheduled: Boolean = false
)