package com.keiferstone.nonet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.ParameterizedRobolectricTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class ConfigurationTests {

    @Test
    public void defaultConfigurationTest() throws Exception {
        Configuration configuration = new Configuration();
        assertEquals(configuration.getEndpoint(), Configuration.DEFAULT_ENDPOINT);
        assertEquals(configuration.getTimeout(), Configuration.DEFAULT_TIMEOUT);
        assertEquals(configuration.getPollFrequency(), Configuration.DEFAULT_POLL_FREQUENCY);
    }

    @Test
    public void defaultConfigurationBuilderTest() throws Exception {
        Configuration configuration = new Configuration.Builder().build();
        assertEquals(configuration.getEndpoint(), Configuration.DEFAULT_ENDPOINT);
        assertEquals(configuration.getTimeout(), Configuration.DEFAULT_TIMEOUT);
        assertEquals(configuration.getPollFrequency(), Configuration.DEFAULT_POLL_FREQUENCY);
    }

    @Test
    public void nullEndpointShouldUseDefaultEndpointTest() throws Exception {
        Configuration configuration = new Configuration.Builder()
                .endpoint(null)
                .build();
        assertEquals(configuration.getEndpoint(), Configuration.DEFAULT_ENDPOINT);
    }

    @Test
    public void emptyEndpointShouldUseDefaultEndpointTest() throws Exception {
        Configuration configuration = new Configuration.Builder()
                .endpoint("")
                .build();
        assertEquals(configuration.getEndpoint(), Configuration.DEFAULT_ENDPOINT);
    }

    @Test
    public void negativeTimeoutShouldUseDefaultTimeoutTest() throws Exception {
        Configuration configuration = new Configuration.Builder()
                .timeout(-1)
                .build();
        assertEquals(configuration.getTimeout(), Configuration.DEFAULT_TIMEOUT);
    }

    @Test
    public void nonPositivePollFrequencyShouldUseDefaultPollFrequencyTest() throws Exception {
        Configuration configuration = new Configuration.Builder()
                .pollFrequency(0)
                .build();
        assertEquals(configuration.getPollFrequency(), Configuration.DEFAULT_POLL_FREQUENCY);
    }

    @Test
    public void customConfigurationsTest() throws Exception {
        final String endpoint = "http://google.com";
        final int timeout = 20;
        final int pollFrequency = 100;

        Configuration configuration = new Configuration.Builder()
                .endpoint(endpoint)
                .timeout(timeout)
                .pollFrequency(pollFrequency)
                .build();
        assertEquals(configuration.getEndpoint(), endpoint);
        assertEquals(configuration.getTimeout(), timeout);
        assertEquals(configuration.getPollFrequency(), pollFrequency);
    }
}