package com.keiferstone.nonet.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import com.keiferstone.nonet.NoNet
import com.keiferstone.nonet.config
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        new_activity_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val config = config {
            timeout = 1
            connectedPollInterval = 10
            disconnectedPollInterval = 2
        }

        NoNet.isConnected(config) {
            Log.d("NoNet", "isConnected = $it")
        }

        NoNet.monitorConnection(config, this) {
            Log.d("NoNet", "monitorConnection = $it")
        }
    }
}
