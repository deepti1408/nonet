package com.keiferstone.nonet.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.keiferstone.nonet.NoNet
import com.keiferstone.nonet.config
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        snackbar = Snackbar.make(new_activity_button, "No connection", Snackbar.LENGTH_INDEFINITE)

        new_activity_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val config = config {
            timeout = 1
            connectedPollInterval = 10
            disconnectedPollInterval = 2
        }

        NoNet.isConnected {
            Log.d("NoNet", if (it) "Connected" else "Not connected")
        }

        NoNet.monitorConnection(this, config) {
            Log.d("NoNet Monitor", if (it) "Connected" else "Not connected")
            if (!it) snackbar.show() else snackbar.dismiss()
        }
    }
}
