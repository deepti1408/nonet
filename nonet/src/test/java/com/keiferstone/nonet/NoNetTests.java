package com.keiferstone.nonet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class NoNetTests {
    @Test
    public void defaultMonitorTest() throws Exception {
        Monitor monitor = NoNet.monitor(null).start();
        assertNotNull(monitor);
    }

    // TODO:
}