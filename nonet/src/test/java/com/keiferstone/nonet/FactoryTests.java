package com.keiferstone.nonet;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SuppressWarnings("ConstantConditions")
@RunWith(RobolectricTestRunner.class)
public class FactoryTests {

    private Activity activity;

    @Before
    public void setupActivity() {
        activity = Robolectric.buildActivity(Activity.class).create().start().resume().visible().get();
    }

    @Test
    public void createToastTests() throws Exception {
        Toast toast = ToastFactory.getToast(null);
        assertNull(toast);

        toast = ToastFactory.getToast(activity);
        assertNotNull(toast);

        toast = ToastFactory.getToast(activity, "test");
        assertNotNull(toast);

        toast = ToastFactory.getToast(activity, R.string.no_server_connection_message);
        assertNotNull(toast);
    }

    @Test
    public void createSnackbarTests() throws Exception {
        Snackbar snackbar = SnackbarFactory.getSnackbar(null);
        assertNull(snackbar);

        /* TODO: Fix Snackbars throwing java.lang.IllegalArgumentException:
         * You need to use a Theme.AppCompat theme (or descendant) with the design library.
        snackbar = SnackbarFactory.getSnackbar(activity);
        assertNotNull(snackbar);

        snackbar = SnackbarFactory.getSnackbar(activity, "test");
        assertNotNull(snackbar);

        snackbar = SnackbarFactory.getSnackbar(activity, R.string.app_name);
        assertNotNull(snackbar);
        */
    }

    @Test
    public void createBannerTests() throws Exception {
        BannerView banner = BannerFactory.getBanner(null);
        assertNull(banner);

        banner = BannerFactory.getBanner(activity);
        assertNotNull(banner);

        banner = BannerFactory.getBanner(activity, "test");
        assertNotNull(banner);

        banner = BannerFactory.getBanner(activity, R.string.no_server_connection_message);
        assertNotNull(banner);

        banner = BannerFactory.getBanner(activity, R.layout.view_default_banner, null);
        assertNotNull(banner);

        banner = BannerFactory.getBanner(activity, R.layout.view_default_banner, (ViewGroup) activity.findViewById(android.R.id.content));
        assertNotNull(banner);
    }
}