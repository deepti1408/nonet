package com.keiferstone.nonet;

import android.content.Context;

/**
 * An Android library for monitoring network connectivity.
 */
public final class NoNet {
    private static NoNet instance = null;

    private Configuration configuration;
    private Monitor monitor;

    private NoNet() {}

    /**
     * Set the global configuration.
     *
     * @return A configuration builder that is automatically applied to the {@link NoNet} instance.
     */
    public static Configuration.Builder configure() {
        instantiate();
        Configuration.Builder builder = new Configuration.Builder();
        instance.configuration = builder.configuration;
        return builder;
    }

    /**
     * Start monitoring network connectivity.
     *
     * @param context Context for listening to connectivity events. Must be an instance of
     *                {@link android.app.Activity} for {@link Monitor.Builder#snackbar()}
     *                to work. Don't forget to call {@link #stopMonitoring()} to stop monitoring.
     *
     * @return A monitor builder.
     */
    public static Monitor.Builder monitor(Context context) {
        instantiate();
        Monitor.Builder builder = new Monitor.Builder(context);
        if (instance.configuration != null) {
            builder.configure(instance.configuration);
        }
        instance.monitor = builder.monitor;
        return builder;
    }

    /**
     * Stop monitoring network connectivity.
     */
    public static void stopMonitoring() {
        instantiate();
        if (instance.monitor != null) {
            instance.monitor.stop();
        }
    }

    // TODO: Add method for one-off network connection checks

    private static void instantiate() {
        if (instance == null) {
            instance = new NoNet();
        }
    }
}
