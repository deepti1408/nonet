package com.keiferstone.nonet.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.keiferstone.nonet.ConnectionStatus;
import com.keiferstone.nonet.Monitor;
import com.keiferstone.nonet.NoNet;

import io.reactivex.functions.Consumer;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        NoNet.monitor(this)
                .poll()
                .snackbar()
                .start()
                .observe()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        monitorCallback.onConnectionEvent(null, integer);
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();

        NoNet.stopMonitoring();
    }

    private Monitor.Callback monitorCallback = new Monitor.Callback() {
        @Override
        public void onConnectionEvent(Response response, @ConnectionStatus int connectionStatus) {
            Toast.makeText(MainActivity.this, "[onConnectionEvent( " + connectionStatus + " )]", Toast.LENGTH_SHORT).show();
        }
    };
}
