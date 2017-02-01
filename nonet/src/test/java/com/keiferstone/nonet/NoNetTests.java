package com.keiferstone.nonet;

import org.junit.Test;

import static org.junit.Assert.*;

public class NoNetTests {
    @Test
    public void defaultMonitorTest() throws Exception {
        Monitor monitor = NoNet.monitor(null).start();
        assertNotNull(monitor);
    }
}