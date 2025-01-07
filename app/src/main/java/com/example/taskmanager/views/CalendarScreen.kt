package com.example.taskmanager.views

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.taskmanager.models.Task
import com.example.taskmanager.repositories.TaskRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import java.util.Calendar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanager.viewmodels.LoginViewModel

@Composable
fun CalendarScreen(
    onNavigateToAddTask: () -> Unit,
    onLogout: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var daysWithTasks by remember { mutableStateOf<List<LocalDate>>(emptyList()) }
    var tasksForSelectedDate by remember { mutableStateOf<List<Task>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        TaskRepository.getDaysWithTasks { days ->
            daysWithTasks = days
        }
    }

    LaunchedEffect(selectedDate) {
        TaskRepository.getTasksByDate(selectedDate.toEpochDay() * 86400000) { tasks ->
            tasksForSelectedDate = tasks
        }
    }

    fun removeTask(task: Task) {
        task.docId?.let { taskId ->
            TaskRepository.removeTask(
                taskId,
                onSuccess = {
                    tasksForSelectedDate = tasksForSelectedDate.filterNot { it.docId == taskId }
                    println("Tarefa removida")
                },
                onFailure = { e ->
                    println("Erro")
                }
            )
        } ?: println("Erro: docId nulo")
    }

    fun showDatePickerDialog(
        context: Context,
        initialDate: LocalDate,
        onDateSelected: (LocalDate) -> Unit
    ) {
        val calendar = Calendar.getInstance()
        calendar.set(initialDate.year, initialDate.monthValue - 1, initialDate.dayOfMonth)

        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateSelected(LocalDate.of(year, month + 1, dayOfMonth))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.setOnDateChangedListener { _, year, month, day ->
                val selected = LocalDate.of(year, month + 1, day)
                if (daysWithTasks.contains(selected)) {
                    datePicker.setBackgroundColor(0xFFD6EAF8.toInt())
                } else {
                    datePicker.setBackgroundColor(0xFFFFFFFF.toInt())
                }
            }
            show()
        }
    }

    Scaffold(
        bottomBar = {
            Column {
                Button(
                    onClick = { onNavigateToAddTask() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Adicionar Tarefa")
                }

                Button(
                    onClick = {
                        loginViewModel.logout()
                        onLogout()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Logout")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Calendário de Tarefas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Dia selecionado: ${selectedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { showDatePickerDialog(context, selectedDate) { newDate ->
                        selectedDate = newDate
                    } }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tasksForSelectedDate) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Título: ${task.title}", style = MaterialTheme.typography.bodyLarge)
                                Text("Descrição: ${task.description}", style = MaterialTheme.typography.bodyMedium)
                            }
                            IconButton(onClick = { removeTask(task) }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Remover Tarefa"
                                )
                            }
                        }
                    }
                }
            }

            if (tasksForSelectedDate.isEmpty()) {
                Text(
                    text = "Nenhuma tarefa para este dia.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
