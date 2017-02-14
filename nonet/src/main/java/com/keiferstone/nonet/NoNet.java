package com.keiferstone.nonet;

import android.content.Context;

/**
 * An Android library for monitoring network connectivity.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public final class NoNet {
    private static NoNet instance = null;

    private Configuration configuration;
    private Monitor monitor;

    private NoNet() {}

    /**
     * Set the global configuration.
     *
     * @return A {@link Configuration.Builder} that is automatically applied to the {@link NoNet} instance.
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
     * @param context Context for listening to connectivity events and displaying notifications.
     *                Must be an instance of {@link android.app.Activity} for
     *                {@link Monitor.Builder#snackbar()} to work. Don't forget to call
     *                {@link #stopMonitoring()} to stop monitoring.
     *
     * @return A {@link Monitor.Builder}.
     */
    public static Monitor.Builder monitor(Context context) {
        instantiate();
        stopMonitoring();
        Monitor.Builder builder = new Monitor.Builder(context);
        if (instance.configuration != null) {
            builder.configure(instance.configuration);
        }
        instance.monitor = builder.monitor;
        return builder;
    }

    /**
     * Make a single check for network connectivity.
     *
     * @param context Context for displaying notifications. Must be an instance of
     *                {@link android.app.Activity} for {@link Check.Builder#snackbar()}
     *                to work.
     *
     * @return A {@link Check.Builder}.
     */
    public static Check.Builder check(Context context) {
        instantiate();
        Check.Builder builder = new Check.Builder(context);
        if (instance.configuration != null) {
            builder.configure(instance.configuration);
        }
        return builder;
    }

    /**
     * Stop monitoring network connectivity.
     */
    public static void stopMonitoring() {
        instantiate();
        if (instance.monitor != null) {
            instance.monitor.stop();
            instance.monitor = null;
        }
    }

    private static void instantiate() {
        if (instance == null) {
            instance = new NoNet();
        }
    }
}
