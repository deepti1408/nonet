package com.keiferstone.nonet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class PollTests {
    @Test
    public void pollTaskShouldReturnValidStatusTest() throws Exception {
        PollTask pollTask = PollTask.run(new Configuration(), new PollTask.OnPollCompletedListener() {
            @Override
            public void onPollCompleted(@ConnectionStatus int connectionStatus) {
                assertTrue(isValidConnectionStatus(connectionStatus));
            }
        });
        assertNotNull(pollTask);

        Robolectric.flushBackgroundThreadScheduler();
    }

    private boolean isValidConnectionStatus(@ConnectionStatus int connectionStatus) {
        switch (connectionStatus) {
            case ConnectionStatus.CONNECTED:
            case ConnectionStatus.DISCONNECTED:
            case ConnectionStatus.UNKNOWN:
                return true;
            default:
                return false;
        }
    }
}