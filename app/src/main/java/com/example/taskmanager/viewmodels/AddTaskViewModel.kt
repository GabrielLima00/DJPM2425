package com.example.taskmanager.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.taskmanager.models.Task
import com.example.taskmanager.repositories.TaskRepository
import com.example.taskmanager.notifications.scheduleNotification
import java.time.LocalDate

class AddTaskViewModel : ViewModel() {
    var state = mutableStateOf(Task())

    fun onTitleChange(newTitle: String) {
        state.value = state.value.copy(title = newTitle)
    }

    fun onDescriptionChange(newDescription: String) {
        state.value = state.value.copy(description = newDescription)
    }

    fun addTask(
        title: String,
        description: String,
        selectedDate: LocalDate,
        enableNotifications: Boolean,
        notificationOption: String,
        context: Context
    ) {
        val task = Task(
            title = title,
            description = description,
            date = selectedDate.atStartOfDay().toEpochSecond(java.time.ZoneOffset.UTC) * 1000
        )

        TaskRepository.addTask(task,
            onSuccess = {
                state.value = Task()
                println("Tarefa adicionada")
                if (enableNotifications) {
                    scheduleNotification(task = task, context = context)
                    println("Data da tarefa (ms): ${task.date}, Data formatada: ${LocalDate.ofEpochDay(task.date!! / 86400000)}")
                }
            },
            onFailure = { e ->
                println("Erro ao adicionar tarefa: ${e.message}")
            }
        )
    }
}
