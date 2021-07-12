package com.indialone.backgroundservice_demo1.own

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.indialone.backgroundservice_demo1.R
import com.indialone.backgroundservice_demo1.databinding.ActivityOwnBinding

class OwnActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityOwnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityOwnBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnStartService.setOnClickListener {
            startService(Intent(this , OwnNotificationService::class.java))
        }

        mBinding.btnStopService.setOnClickListener {
            stopService(Intent(this , OwnNotificationService::class.java))
        }

    }
}