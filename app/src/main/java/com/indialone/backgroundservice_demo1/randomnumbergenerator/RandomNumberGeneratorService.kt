package com.indialone.backgroundservice_demo1.randomnumbergenerator

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.indialone.backgroundservice_demo1.R
import com.indialone.backgroundservice_demo1.notification.NotificationService.Companion.CHANNEL_ID

class RandomNumberGeneratorService : Service() {

    private val HANDLER_THREAD_NAME = "random_number_generator_thread"

    private var binder = RandomNumberGeneratorServiceBinder()

    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    var randomNumber: Int = -1
    private val TAG = RandomNumberGeneratorService::class.java.simpleName

    private lateinit var mNM: NotificationManager
    private var NOTIFICATION = R.string.local_service_started
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private var generatedRandomNumber = false


    inner class RandomNumberGeneratorServiceBinder : Binder() {
        val service: RandomNumberGeneratorService = this@RandomNumberGeneratorService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        mNM = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        handlerThread = HandlerThread(HANDLER_THREAD_NAME)
        handlerThread.start()

        handler = Handler(handlerThread.looper)
        handler.post {
            startGeneratingRandomNumber()
        }
        showNotification()

    }

    private fun showNotification() {
        val text = resources.getString(R.string.local_service_started)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, RandomNumberGeneratorActivity::class.java),
            0
        )

        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setTicker(text)
            .setWhen(System.currentTimeMillis())
            .setContentTitle("Location Service Started")
            .setContentTitle(text)
            .setContentIntent(pendingIntent)

        mNM.notify(NOTIFICATION, notificationBuilder.build())

    }

    private fun startGeneratingRandomNumber() {
        generatedRandomNumber = true
        while (generatedRandomNumber) {
            Thread.sleep(1000)
            randomNumber = (Math.random() * 100).toInt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        generatedRandomNumber = false

        handler.removeCallbacksAndMessages(null)
        handler.looper.quit()
        handlerThread.quit()

        mNM.cancel(NOTIFICATION)

    }

}