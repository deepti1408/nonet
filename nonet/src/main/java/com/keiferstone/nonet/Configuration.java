package com.keiferstone.nonet;

import android.text.TextUtils;

@SuppressWarnings("WeakerAccess")
public final class Configuration {
    static final String DEFAULT_ENDPOINT = "http://gstatic.com/generate_204";
    static final int DEFAULT_TIMEOUT = 10;
    static final int DEFAULT_POLL_FREQUENCY = 60;

    private String endpoint;
    private int timeout;
    private int pollFrequency;

    Configuration() {
        endpoint = DEFAULT_ENDPOINT;
        timeout = DEFAULT_TIMEOUT;
        pollFrequency = DEFAULT_POLL_FREQUENCY;
    }

    private void setEndpoint(String endpoint) {
        if (TextUtils.isEmpty(endpoint)) {
            this.endpoint = DEFAULT_ENDPOINT;
        } else {
            this.endpoint = endpoint;
        }
    }

    private void setTimeout(int timeout) {
        if (timeout < 0) {
            this.timeout = DEFAULT_TIMEOUT;
        } else {
            this.timeout = timeout;
        }
    }

    private void setPollFrequency(int pollFrequency) {
        if (pollFrequency <= 0) {
            this.pollFrequency = DEFAULT_POLL_FREQUENCY;
        } else {
            this.pollFrequency = pollFrequency;
        }
    }

    String getEndpoint() {
        return endpoint;
    }

    int getTimeout() {
        return timeout;
    }

    int getPollFrequency() {
        return pollFrequency;
    }

    public static class Builder {
        Configuration configuration;

        public Builder() {
            configuration = new Configuration();
        }

        /**
         * Set the endpoint to check connectivity against. Defaults to
         * http://gstatic.com/generate_204 if null or empty.
         *
         * @param endpoint The endpoint to poll.
         *
         * @return This {@link Configuration.Builder}.
         */
        public Builder endpoint(String endpoint) {
            configuration.setEndpoint(endpoint);
            return this;
        }

        /**
         * Set the timeout when checking connectivity. Defaults to 10 if negative.
         *
         * @param timeout The timeout for connectivity polls.
         *
         * @return This {@link Configuration.Builder}.
         */
        public Builder timeout(int timeout) {
            configuration.setTimeout(timeout);
            return this;
        }

        /**
         * Set the poll frequency when checking connectivity. Defaults to 60 if non-positive.
         *
         * @param pollFrequency The frequency to poll for connectivity.
         *
         * @return This {@link Configuration.Builder}.
         */
        public Builder pollFrequency(int pollFrequency) {
            configuration.setPollFrequency(pollFrequency);
            return this;
        }

        /**
         * Build the configuration.
         *
         * @return The {@link Configuration}.
         */
        public Configuration build() {
            return configuration;
        }
    }
}
