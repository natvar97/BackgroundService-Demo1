package com.indialone.backgroundservice_demo1.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Exception

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class JobSchedulerDemoService : JobService() {

    private var jobCancelled = false

    override fun onStartJob(params: JobParameters?): Boolean {
        doInBackground(params)
        return true
    }

    private fun doInBackground(params: JobParameters?) = Thread {
        for (i in 1..10) {
            if (jobCancelled) return@Thread

            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        jobFinished(params, false)
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        jobCancelled = true
        return true
    }

}