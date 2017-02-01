package com.keiferstone.nonet;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.keiferstone.nonet.ConnectionStatus.*;

class PollTask extends AsyncTask<Void, Void, Response> {
    private static final String TAG = PollTask.class.getSimpleName();

    private Configuration configuration;
    private OnPollCompletedListener listener;
    private OkHttpClient client;

    static void run(Configuration configuration, OnPollCompletedListener listener) {
        PollTask pollTask = new PollTask(configuration, listener);
        pollTask.execute();
    }

    private PollTask(Configuration configuration, OnPollCompletedListener listener) {
        this.configuration = configuration;
        this.listener = listener;

        client = new OkHttpClient.Builder()
                .connectTimeout(configuration.getTimeout(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    protected Response doInBackground(Void... params) {
        Request request = configuration.getRequest();
        if (request == null) {
            request = new Request.Builder()
                    .url(configuration.getEndpoint())
                    .build();
        }

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
        @ConnectionStatus int connectionStatus = (response != null && response.isSuccessful())
                ? CONNECTED : DISCONNECTED;
        Log.d(TAG, "Poll result: " + connectionStatus);

        if (listener != null) {
            listener.onPollCompleted(response, connectionStatus);
        }
    }

    interface OnPollCompletedListener {
        void onPollCompleted(Response response, @ConnectionStatus int connectionStatus);
    }
}
