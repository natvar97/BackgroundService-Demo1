package com.indialone.backgroundservice_demo1.randomnumbergenerator

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.indialone.backgroundservice_demo1.R
import com.indialone.backgroundservice_demo1.databinding.ActivityRandomNumberGeneratorBinding

class RandomNumberGeneratorActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRandomNumberGeneratorBinding
    private var randomNumberGeneratorService: RandomNumberGeneratorService? = null
    private lateinit var connection: ServiceConnection

    var bounded = false
    private val TAG = RandomNumberGeneratorActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityRandomNumberGeneratorBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d(TAG, "Service Connected")
                randomNumberGeneratorService =
                    (service as RandomNumberGeneratorService.RandomNumberGeneratorServiceBinder).service
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d(TAG, "Service Disconnected")
                randomNumberGeneratorService = null
            }

        }

        bindService(
            Intent(
                this,
                RandomNumberGeneratorService::class.java
            ),
            connection,
            Context.BIND_AUTO_CREATE
        )

        setUpViews()

    }

    private fun setUpViews() {
        mBinding.bindService.setOnClickListener {
            if (!bounded) {
                bindService(
                    Intent(this, RandomNumberGeneratorService::class.java),
                    connection,
                    Context.BIND_AUTO_CREATE
                )
                bounded = true
            }
        }

        mBinding.getRandomNumber.setOnClickListener {
            if (!bounded) {
                mBinding.generatedRandomNumber.text = "Service is not Bound"
            } else {
                mBinding.generatedRandomNumber.text =
                    randomNumberGeneratorService?.randomNumber.toString()
            }
        }

        mBinding.unbindService.setOnClickListener {
            unbindSafely()
        }


    }

    private fun unbindSafely() {
        if (bounded) {
            unbindService(connection)
            bounded = false
        }
    }

    override fun onDestroy() {
        unbindSafely()
        super.onDestroy()

    }


}