package com.keiferstone.nonet.sample;


import android.app.Application;

import com.keiferstone.nonet.NoNet;

public class NoNetApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        NoNet.configure()
                .endpoint("https://keiferstone.com")
                .timeout(5)
                .connectedPollFrequency(60)
                .disconnectedPollFrequency(1);
    }
}
