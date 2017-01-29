package com.keiferstone.nonet;

import android.content.Context;

public final class NoNet {
    private static NoNet instance = null;

    private Configuration configuration;
    private Monitor monitor;

    private NoNet() {}

    public static Configuration.Builder configure() {
        instantiate();
        Configuration.Builder builder = new Configuration.Builder();
        instance.configuration = builder.configuration;
        return builder;
    }

    public static Monitor.Builder monitor(Context context) {
        instantiate();
        Monitor.Builder builder = new Monitor.Builder(context);
        if (instance.configuration != null) {
            builder.configure(instance.configuration);
        }
        instance.monitor = builder.monitor;
        return builder;
    }

    public static void finish() {
        instantiate();
        if (instance.monitor != null) {
            instance.monitor.stop();
        }
    }

    private static void instantiate() {
        if (instance == null) {
            instance = new NoNet();
        }
    }
}
