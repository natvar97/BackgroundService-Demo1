package com.indialone.backgroundservice_demo1.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.indialone.backgroundservice_demo1.R
import com.indialone.backgroundservice_demo1.databinding.ActivityJobSchedulerDemoBinding

class JobSchedulerDemoActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityJobSchedulerDemoBinding
    private lateinit var jobScheduler: JobScheduler
    private val TAG = JobSchedulerDemoActivity::class.java.simpleName

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityJobSchedulerDemoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnStartService.setOnClickListener {
            val componentName = ComponentName(this, JobSchedulerDemoService::class.java)
            val jobInfo = JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(16 * 60 * 1000)
                .build()

            val jobScheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

            val resultCode: Int = jobScheduler.schedule(jobInfo)
            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Log.e(TAG, "Job Scheduled")
            } else {
                Log.e(TAG, "Job not Scheduled")
            }

        }

        mBinding.btnStopService.setOnClickListener {
            val scheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.cancel(123)
            Log.e(TAG, "Job Cancelled")
        }


    }
}