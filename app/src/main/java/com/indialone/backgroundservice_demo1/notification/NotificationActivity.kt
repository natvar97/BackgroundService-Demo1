package com.indialone.backgroundservice_demo1.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.indialone.backgroundservice_demo1.R
import com.indialone.backgroundservice_demo1.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnStartService.setOnClickListener {
            val input = mBinding.editTextInput.text.toString()
            Log.e("input", input)
            val intent = Intent(this, NotificationService::class.java)
            intent.putExtra("input", input)
            startService(intent)
        }

        mBinding.btnStopService.setOnClickListener {
            stopService(Intent(this, NotificationService::class.java))
        }

    }
}