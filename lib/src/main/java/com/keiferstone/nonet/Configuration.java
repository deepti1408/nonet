package com.keiferstone.nonet;

public final class Configuration {
    private static final String DEFAULT_ENDPOINT = "https://google.com";
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

        Builder() {
            configuration = new Configuration();
        }

        public Builder endpoint(String endpoint) {
            configuration.endpoint = endpoint;
            return this;
        }

        public Builder timeout(int timeout) {
            configuration.timeout = timeout;
            return this;
        }

        public Builder pollFrequency(int pollFrequency) {
            configuration.pollFrequency = pollFrequency;
            return this;
        }

        public Configuration build() {
            return configuration;
        }
    }
}
