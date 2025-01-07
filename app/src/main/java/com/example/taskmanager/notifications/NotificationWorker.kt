package com.example.taskmanager.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.taskmanager.R

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        println("Worker de notificação iniciado.")

        val title = inputData.getString("title") ?: "Tarefa"
        val description = inputData.getString("description") ?: "Detalhes da tarefa"

        if (NotificationManagerCompat.from(applicationContext).areNotificationsEnabled()) {
            createNotificationChannel()

            val builder = NotificationCompat.Builder(applicationContext, "task_channel")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(applicationContext)) {
                notify(System.currentTimeMillis().toInt(), builder.build())
            }
        } else {
            println("Permissão de notificação não concedida. Notificação não enviada.")
        }

        return Result.success()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Canal de Tarefas"
            val descriptionText = "Notificações de lembretes de tarefas"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("task_channel", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            println("Canal de notificação criado.")
        }
    }
}
