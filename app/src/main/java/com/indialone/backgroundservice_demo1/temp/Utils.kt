package com.indialone.backgroundservice_demo1.temp

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.indialone.backgroundservice_demo1.jobscheduler.JobSchedulerDemoService

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
object Utils {

    @RequiresApi(Build.VERSION_CODES.M)
    fun scheduleJob(context: Context) {
        val serviceComponent = ComponentName(context, JobSchedulerDemoService::class.java)

        val builder: JobInfo.Builder = JobInfo.Builder(0, serviceComponent)
        builder.setMinimumLatency(1 * 1000)
        builder.setOverrideDeadline(3 * 1000)

        val jobScheduler: JobScheduler =
            context.getSystemService(JobScheduler::class.java) as JobScheduler

        jobScheduler.schedule(builder.build())

    }

}