package com.keiferstone.nonet;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SuppressWarnings("ConstantConditions")
public class FactoryTests {

    private Activity activity;

    @Before
    public void setupActivity() {
        activity = Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test
    public void createToastTests() throws Exception {
        Toast toast = ToastFactory.getToast(null);
        assertNull(toast);

        toast = ToastFactory.getToast(activity);
        assertNotNull(toast);

        toast = ToastFactory.getToast(activity, "test");
        assertNotNull(toast);

        toast = ToastFactory.getToast(activity, R.string.app_name);
        assertNotNull(toast);
    }

    @Test
    public void createSnackbarTests() throws Exception {
        Snackbar snackbar = SnackbarFactory.getSnackbar(null);
        assertNull(snackbar);

        snackbar = SnackbarFactory.getSnackbar(activity);
        assertNotNull(snackbar);

        snackbar = SnackbarFactory.getSnackbar(activity, "test");
        assertNotNull(snackbar);

        snackbar = SnackbarFactory.getSnackbar(activity, R.string.app_name);
        assertNotNull(snackbar);
    }
}