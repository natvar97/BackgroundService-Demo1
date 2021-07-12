package com.indialone.backgroundservice_demo1

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.indialone.backgroundservice_demo1.notification.NotificationService.Companion.CHANNEL_ID

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        createNotification()

    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Notification Channel Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }

}