package com.keiferstone.nonet;

public final class Configuration {
    private static final String DEFAULT_ENDPOINT = "http://gstatic.com/generate_204";
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_POLL_FREQUENCY = 60;

    private String endpoint;
    private int timeout;
    private int pollFrequency;

    Configuration() {
        endpoint = DEFAULT_ENDPOINT;
        timeout = DEFAULT_TIMEOUT;
        pollFrequency = DEFAULT_POLL_FREQUENCY;
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
         * Set the endpoint to check connectivity against.
         *
         * @param endpoint The endpoint to poll.
         *
         * @return This {@link Configuration.Builder}.
         */
        public Builder endpoint(String endpoint) {
            configuration.endpoint = endpoint;
            return this;
        }

        /**
         * Set the timeout when checking connectivity.
         *
         * @param timeout The timeout for connectivity polls.
         *
         * @return This {@link Configuration.Builder}.
         */
        public Builder timeout(int timeout) {
            configuration.timeout = timeout;
            return this;
        }

        /**
         * Set the poll frequency when checking connectivity.
         *
         * @param pollFrequency The frequency to poll for connectivity.
         *
         * @return This {@link Configuration.Builder}.
         */
        public Builder pollFrequency(int pollFrequency) {
            configuration.pollFrequency = pollFrequency;
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
