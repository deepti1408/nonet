package com.keiferstone.nonet

import android.os.Handler
import android.util.Log
import androidx.lifecycle.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit


object NoNet {

    /**
     * Check if we have a network connection.
     *
     * @param config An optional custom configuration
     */
    suspend fun isConnected(config: Config = Config()): Boolean {
        return try {
            val request = Request.Builder()
                    .url(config.url)
                    .build()
            OkHttpClient.Builder()
                    .callTimeout(config.timeout, TimeUnit.SECONDS)
                    .build()
                    .newCall(request)
                    .execute().use { response ->
                response.isSuccessful
            }
        } catch (e: IOException) {
            Log.e(NoNet.javaClass.name, "Connection check failed", e)
            false
        }
    }

    /**
     * Check if we have a network connection.
     *
     * @param config An optional custom configuration
     */
    fun isConnected(config: Config = Config(), callback: (Boolean) -> Unit) {
        val request = Request.Builder()
                .url(config.url)
                .build()
        OkHttpClient.Builder()
                .callTimeout(config.timeout, TimeUnit.SECONDS)
                .build()
                .newCall(request)
                .enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e(NoNet.javaClass.name, "Connection check failed", e)
                callback(false)
            }
        })
    }


    /**
     * Monitor network connectivity as long as the provided [lifecycleOwner] is resumed.
     *
     * @param config An optional custom configuration
     * @param lifecycleOwner The [LifecycleOwner] to attach to
     */
    fun monitorConnection(config: Config = Config(), lifecycleOwner: LifecycleOwner, callback: (Boolean) -> Unit) {
        lifecycleOwner.lifecycle.addObserver(ConnectionObserver(config, lifecycleOwner, callback))
    }

    @Suppress("unused")
    private class ConnectionObserver(private val config: Config,
                                     private val lifecycleOwner: LifecycleOwner,
                                     private val callback: (Boolean) -> Unit) : LifecycleObserver {
        private val handler = Handler()
        private val runnable = object : Runnable {
            override fun run() {
                isConnected(config) {
                    val pollInterval =
                            if (it) config.connectedPollInterval
                            else config.disconnectedPollInterval
                    callback(it)
                    handler.postDelayed(this, pollInterval * 1000)
                }
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun startMonitoring() {
            Log.d(NoNet.javaClass.name, "Starting connection monitoring")
            handler.post(runnable)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun stopMonitoring() {
            Log.d(NoNet.javaClass.name, "Stopping connection monitoring")
            handler.removeCallbacks(runnable)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun destroy() {
            Log.d(NoNet.javaClass.name, "Destroying connection observer")
            lifecycleOwner.lifecycle.removeObserver(this)
        }
    }
}