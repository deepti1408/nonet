package com.keiferstone.nonet.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        Button button = (Button) findViewById(R.id.new_activity_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        NoNet.monitor(this)
                .poll()
                .banner()
                .snackbar()
                .observe()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        monitorCallback.onConnectionEvent(integer);
                    }
                });

        NoNet.check(this)
                .toast()
                .start();
    }

    private Monitor.Callback monitorCallback = new Monitor.Callback() {
        @Override
        public void onConnectionEvent(@ConnectionStatus int connectionStatus) {
            Log.d("K", "[onConnectionEvent( " + connectionStatus + " )]");
            //Toast.makeText(MainActivity.this, "[onConnectionEvent( " + connectionStatus + " )]", Toast.LENGTH_SHORT).show();
        }
    };
}
