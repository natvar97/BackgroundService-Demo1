package com.indialone.backgroundservice_demo1.media

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class MediaService : Service() {

    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)

        player.isLooping = true

        player.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        player.stop()
    }

}