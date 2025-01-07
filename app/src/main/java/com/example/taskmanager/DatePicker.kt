package com.example.taskmanager.views

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.util.Calendar

@Composable
fun DatePicker(
    selectedDate: LocalDate,
    daysWithTasks: List<LocalDate>,
    onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showDatePickerDialog(context, selectedDate, daysWithTasks, onDateSelected)
            }
            .padding(16.dp)
    ) {
        Text(text = "Selecionar Data: ${selectedDate.toString()}")
    }
}

fun showDatePickerDialog(
    context: Context,
    initialDate: LocalDate,
    daysWithTasks: List<LocalDate>,
    onDateSelected: (LocalDate) -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.set(initialDate.year, initialDate.monthValue - 1, initialDate.dayOfMonth)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            onDateSelected(LocalDate.of(year, month + 1, dayOfMonth))
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    datePickerDialog.datePicker.setOnDateChangedListener { _, year, month, day ->
        val selected = LocalDate.of(year, month + 1, day)
        if (daysWithTasks.contains(selected)) {
            datePickerDialog.datePicker.calendarView.setDate(selected.toEpochDay() * 86400000)
            datePickerDialog.datePicker.setBackgroundColor(0xFFD6EAF8.toInt())
        } else {
            datePickerDialog.datePicker.setBackgroundColor(0xFFFFFFFF.toInt())
        }
    }

    datePickerDialog.show()
}



