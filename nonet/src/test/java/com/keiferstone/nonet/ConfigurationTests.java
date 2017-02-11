package com.keiferstone.nonet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class ConfigurationTests {

    @Test
    public void defaultConfigurationTest() throws Exception {
        Configuration configuration = new Configuration();
        assertEquals(configuration.getEndpoint(), Configuration.DEFAULT_ENDPOINT);
        assertEquals(configuration.getTimeout(), Configuration.DEFAULT_TIMEOUT);
        assertEquals(configuration.getConnectedPollFrequency(), Configuration.DEFAULT_CONNECTED_POLL_FREQUENCY);
    }

    @Test
    public void defaultConfigurationBuilderTest() throws Exception {
        Configuration configuration = new Configuration.Builder().build();
        assertEquals(configuration.getEndpoint(), Configuration.DEFAULT_ENDPOINT);
        assertEquals(configuration.getTimeout(), Configuration.DEFAULT_TIMEOUT);
        assertEquals(configuration.getConnectedPollFrequency(), Configuration.DEFAULT_CONNECTED_POLL_FREQUENCY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullEndpointShouldThrowExceptionTest() throws Exception {
        new Configuration.Builder().endpoint(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyEndpointShouldThrowExceptionTest() throws Exception {
        new Configuration.Builder().endpoint("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidEndpointShouldThrowExceptionTest() throws Exception {
        new Configuration.Builder().endpoint("invalid endpoint");
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeTimeoutShouldThrowExceptionTest() throws Exception {
        new Configuration.Builder().timeout(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonPositiveConnectedPollFrequencyShouldThrowExceptionTest() throws Exception {
        new Configuration.Builder().connectedPollFrequency(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonPositiveDisconnectedPollFrequencyShouldThrowExceptionTest() throws Exception {
        new Configuration.Builder().disconnectedPollFrequency(0);
    }

    @Test
    public void customConfigurationsTest() throws Exception {
        final String endpoint = "http://google.com";
        final int timeout = 20;
        final int pollFrequency = 100;

        Configuration configuration = new Configuration.Builder()
                .endpoint(endpoint)
                .timeout(timeout)
                .connectedPollFrequency(pollFrequency)
                .disconnectedPollFrequency(pollFrequency)
                .build();
        assertEquals(configuration.getEndpoint(), endpoint);
        assertEquals(configuration.getTimeout(), timeout);
        assertEquals(configuration.getConnectedPollFrequency(), pollFrequency);
    }
}