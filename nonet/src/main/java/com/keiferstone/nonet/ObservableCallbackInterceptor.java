package com.keiferstone.nonet;

import io.reactivex.ObservableEmitter;
import okhttp3.Response;

class ObservableCallbackInterceptor implements Monitor.Callback {
    private final Monitor.Callback callback;
    private final ObservableEmitter<Integer> emitter;

    ObservableCallbackInterceptor(Monitor.Callback callback, ObservableEmitter<Integer> emitter) {
        this.callback = callback;
        this.emitter = emitter;
    }

    void stopEmitting() {
        if (emitter != null) {
            emitter.onComplete();
        }
    }

    @Override
    public void onConnectionEvent(Response response, @ConnectionStatus int connectionStatus) {
        if (callback != null) {
            callback.onConnectionEvent(response, connectionStatus);
        }

        if (emitter != null) {
            // TODO: Add support for emitting a response
            emitter.onNext(connectionStatus);
        }
    }
}