package com.keiferstone.nonet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit


@Suppress("unused")
object NoNet {
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    fun init(context: Context) {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /**
     * Check the system [ConnectivityManager] to determine if we have an active network connection.
     */
    fun hasConnection(): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }

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
     * @param lifecycleOwner The [LifecycleOwner] to attach to
     * @param config An optional custom configuration
     */
    fun monitorConnection(lifecycleOwner: LifecycleOwner, config: Config = Config(), callback: (Boolean) -> Unit) {
        lifecycleOwner.lifecycle.addObserver(ConnectionObserver(config, lifecycleOwner, callback))
    }

    private class ConnectionObserver(private val config: Config,
                                     private val lifecycleOwner: LifecycleOwner,
                                     private val callback: (Boolean) -> Unit) : LifecycleObserver {
        private val monitorHandler = Handler()
        private val runnable = object : Runnable {
            private val callbackHandler = Handler(Looper.getMainLooper())

            override fun run() {
                isConnected(config) {
                    if (config.callbackOnlyIfChanged) {
                        if (isConnected != it) callbackHandler.post { callback(it) }
                    } else callbackHandler.post { callback(it) }
                    isConnected = it
                    val pollInterval =
                            if (it) config.connectedPollInterval
                            else config.disconnectedPollInterval
                    if (pollInterval > 0) {
                        monitorHandler.postDelayed(this, pollInterval * 1000)
                    }
                }
            }
        }
        private val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.d(NoNet.javaClass.name, "Network available")
                monitorHandler.removeCallbacks(runnable)
                monitorHandler.post(runnable)
            }

            override fun onLost(network: Network?) {
                Log.d(NoNet.javaClass.name, "Network lost")
                monitorHandler.removeCallbacks(runnable)
                monitorHandler.post(runnable)
            }

            override fun onUnavailable() {
                Log.d(NoNet.javaClass.name, "Network unavailable")
                monitorHandler.removeCallbacks(runnable)
                monitorHandler.post(runnable)
            }
        }
        private val request =
                NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .build()
        private var isConnected: Boolean? = null

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun startMonitoring() {
            Log.d(NoNet.javaClass.name, "Starting connection monitoring")
            connectivityManager.registerNetworkCallback(request, networkCallback)
            monitorHandler.post(runnable)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun stopMonitoring() {
            Log.d(NoNet.javaClass.name, "Stopping connection monitoring")
            connectivityManager.unregisterNetworkCallback(networkCallback)
            monitorHandler.removeCallbacks(runnable)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun destroy() {
            Log.d(NoNet.javaClass.name, "Destroying connection observer")
            lifecycleOwner.lifecycle.removeObserver(this)
        }
    }
}