package com.keiferstone.nonet.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.keiferstone.nonet.Configuration;
import com.keiferstone.nonet.ConnectionStatus;
import com.keiferstone.nonet.Monitor;
import com.keiferstone.nonet.NoNet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoNet.monitor(this)
                .poll()
                .snackbar()
                .callback(monitorCallback)
                .start();

        Configuration configuration = new Configuration.Builder()
                .endpoint("https://keiferstone.com")
                .timeout(20)
                .build();

        NoNet.check(this)
                .configure(configuration)
                .toast()
                .callback(monitorCallback)
                .start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        NoNet.stopMonitoring();
    }

    private Monitor.Callback monitorCallback = new Monitor.Callback() {
        @Override
        public void onConnectionEvent(@ConnectionStatus int connectionStatus) {
            Toast.makeText(MainActivity.this, "[onConnectionEvent( " + connectionStatus + " )]", Toast.LENGTH_SHORT).show();
        }
    };
}
