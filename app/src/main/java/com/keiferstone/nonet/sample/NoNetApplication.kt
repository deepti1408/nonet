package com.keiferstone.nonet.sample


import android.app.Application
import com.keiferstone.nonet.NoNet

class NoNetApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        NoNet.init(this)
    }
}
