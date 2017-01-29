package com.keiferstone.nonet.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
                .callback(new Monitor.Callback() {
                    @Override
                    public void onConnectionChanged(@ConnectionStatus int connectionStatus) {
                        Toast.makeText(MainActivity.this, "[onConnectionChanged( " + connectionStatus + " )]", Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        NoNet.stopMonitoring();
    }
}
