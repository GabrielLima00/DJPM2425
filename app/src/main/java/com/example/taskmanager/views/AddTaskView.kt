package com.example.taskmanager.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import com.example.taskmanager.viewmodels.AddTaskViewModel

@Composable
fun AddTaskView(
    modifier: Modifier = Modifier,
    onTaskAdded: () -> Unit
) {
    val viewModel: AddTaskViewModel = viewModel()
    val state by viewModel.state
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    val daysWithTasks = remember { mutableStateOf<List<LocalDate>>(emptyList()) }
    val context = LocalContext.current
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val notificationEnabled = remember { mutableStateOf(false) }
    val selectedNotificationOption = remember { mutableStateOf("Nenhuma") }
    val notificationOptions = listOf("Nenhuma", "1 Semana Antes", "3 Dias Antes", "2 Dias Antes", "1 Dia Antes", "No Dia")

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            placeholder = { Text("Título da Tarefa") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = description.value,
            onValueChange = { description.value = it },
            placeholder = { Text("Descrição") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        DatePicker(
            selectedDate = selectedDate.value,
            daysWithTasks = daysWithTasks.value,
            onDateSelected = { date -> selectedDate.value = date }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Ativar Notificações")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = notificationEnabled.value,
                onCheckedChange = { notificationEnabled.value = it }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        var expanded by remember { mutableStateOf(false) }
        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(selectedNotificationOption.value)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                notificationOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedNotificationOption.value = option
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.addTask(
                title = title.value,
                description = description.value,
                selectedDate = selectedDate.value,
                enableNotifications = notificationEnabled.value,
                notificationOption = selectedNotificationOption.value,
                context = context
            )
            onTaskAdded()
        }) {
            Text("Adicionar Tarefa")
        }
    }
}