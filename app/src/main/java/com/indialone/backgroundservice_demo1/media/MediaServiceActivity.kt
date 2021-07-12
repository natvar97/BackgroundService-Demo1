package com.indialone.backgroundservice_demo1.media

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.indialone.backgroundservice_demo1.R
import com.indialone.backgroundservice_demo1.databinding.ActivityMediaServiceBinding

class MediaServiceActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityMediaServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMediaServiceBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnStartService.setOnClickListener(this)
        mBinding.btnStopService.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_start_service -> {
                startService(Intent(this, MediaService::class.java))
            }

            R.id.btn_stop_service -> {
                stopService(Intent(this, MediaService::class.java))
            }
        }
    }
}