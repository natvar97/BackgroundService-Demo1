package com.indialone.backgroundservice_demo1.own

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.indialone.backgroundservice_demo1.R
import com.indialone.backgroundservice_demo1.notification.NotificationService.Companion.CHANNEL_ID

class OwnNotificationService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.emirates_palace_abu_dhabi)

        val ownIntent = Intent(this, OwnActivity::class.java)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, ownIntent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Own Service")
            .setSmallIcon(R.drawable.icon_notification)
            .setLargeIcon(bitmap)
            .setContentText("Hotel Emirates Palace, Abu Dhabi")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        return START_NOT_STICKY
    }
}