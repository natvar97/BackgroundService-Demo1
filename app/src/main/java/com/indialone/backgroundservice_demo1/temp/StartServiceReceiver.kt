package com.indialone.backgroundservice_demo1.temp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
class StartServiceReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Utils.scheduleJob(context!!)
    }

}