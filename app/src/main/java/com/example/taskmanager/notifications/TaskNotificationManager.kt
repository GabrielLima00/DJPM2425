package com.example.taskmanager.notifications

import android.content.Context
import androidx.work.*
import com.example.taskmanager.models.Task
import java.util.concurrent.TimeUnit
import java.util.Calendar

fun scheduleNotification(context: Context, task: Task) {
    val currentTime = System.currentTimeMillis()

    val notificationTimes = listOf(
        getNotificationTime(task.date!!, 9, 0) - TimeUnit.DAYS.toMillis(7),
        getNotificationTime(task.date!!, 9, 0) - TimeUnit.DAYS.toMillis(3),
        getNotificationTime(task.date!!, 9, 0) - TimeUnit.DAYS.toMillis(2),
        getNotificationTime(task.date!!, 9, 0) - TimeUnit.DAYS.toMillis(1),
        getNotificationTime(task.date!!, 9, 0)
    )

    //val notificationTimes = listOf(
    //System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10)  // 10 segundos depois
    //)

    notificationTimes.forEach { time ->
        val calendar = Calendar.getInstance().apply { timeInMillis = time }
        println("Agendando notificação para: ${calendar.time} (Tarefa: ${task.title})")

        if (time > currentTime) {
            val delay = time - currentTime
            println("Notificação será enviada em: ${delay / 1000} segundos.")

            val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setInputData(workDataOf("title" to task.title, "description" to task.description))
                .build()
            WorkManager.getInstance(context).enqueue(workRequest)
        } else {
            println("Notificação não agendada. Data já passou: ${calendar.time}")
        }
    }
}

fun getNotificationTime(dateInMillis: Long, hour: Int, minute: Int): Long {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = dateInMillis
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.timeInMillis
}


