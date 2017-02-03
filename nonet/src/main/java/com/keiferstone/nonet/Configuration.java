package com.keiferstone.nonet;

import android.util.Patterns;

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
        if (endpoint == null || !Patterns.WEB_URL.matcher(endpoint).matches()) {
            throw new IllegalArgumentException("Endpoint must be a valid URL. Supplied URL: " + endpoint);
        } else {
            this.endpoint = endpoint;
        }
    }

    private void setTimeout(int timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException("Timeout must be non-negative. Supplied timeout: " + timeout);
        } else {
            this.timeout = timeout;
        }
    }

    private void setPollFrequency(int pollFrequency) {
        if (pollFrequency <= 0) {
            throw new IllegalArgumentException("Poll frequency must be positive. Supplied poll frequency: " + pollFrequency);
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
         * Set the endpoint to check connectivity against. Must be a valid URL.
         *
         * @param endpoint The endpoint to poll.
         *
         * @return This {@link Configuration.Builder}.
         *
         * @throws IllegalArgumentException If endpoint is invalid.
         */
        public Builder endpoint(String endpoint) {
            configuration.setEndpoint(endpoint);
            return this;
        }

        /**
         * Set the timeout when checking connectivity. Must be non-negative.
         *
         * @param timeout The timeout for connectivity polls.
         *
         * @return This {@link Configuration.Builder}.
         *
         * @throws IllegalArgumentException If timeout is negative.
         */
        public Builder timeout(int timeout) {
            configuration.setTimeout(timeout);
            return this;
        }

        /**
         * Set the poll frequency when checking connectivity. Must be positive.
         *
         * @param pollFrequency The frequency to poll for connectivity.
         *
         * @return This {@link Configuration.Builder}.
         *
         * @throws IllegalArgumentException If pollFrequency is non-positive.
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
