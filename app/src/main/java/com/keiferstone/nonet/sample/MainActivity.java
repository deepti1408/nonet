package com.keiferstone.nonet.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.keiferstone.nonet.ConnectionStatus;
import com.keiferstone.nonet.Monitor;
import com.keiferstone.nonet.NoNet;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoNet.monitor(this)
                .poll()
                .snackbar()
                .start()
                .observe()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        monitorCallback.onConnectionEvent(integer);
                    }
                });
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
