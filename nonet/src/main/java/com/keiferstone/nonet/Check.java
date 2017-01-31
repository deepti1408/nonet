package com.keiferstone.nonet;

import android.content.Context;

/**
 * A subclass of {@link Monitor} that makes a one-time network connectivity check
 * and has polling disabled.
 */
public class Check extends Monitor {
    private Check(Context context) {
        super(context);
    }

    @Override
    void start() {
        poll();
    }

    @Override
    void stop() {
        // Nothing to do here...
    }

    public static class Builder extends Monitor.Builder {
        Builder(Context context) {
            super(context);
        }

        @Override
        public Builder configure(Configuration configuration) {
            super.configure(configuration);
            return this;
        }

        /**
         * This method is disabled while using {@link NoNet#check(Context)}.
         *
         * @throws IllegalStateException Cannot poll while using {@link NoNet#check(Context)}
         *
         * @return This {@link Check.Builder}
         */
        @Override
        public Builder poll() {
            throw new IllegalStateException("Cannot poll while using NoNet.check()");
        }

        @Override
        public Builder toast() {
            super.toast();
            return this;
        }

        @Override
        public Builder snackbar() {
            super.snackbar();
            return this;
        }

        @Override
        public Builder callback(Callback callback) {
            super.callback(callback);
            return this;
        }

        @Override
        public Monitor start() {
            return super.start();
        }
    }
}
