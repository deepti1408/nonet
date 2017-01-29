package com.keiferstone.nonet;


import android.support.annotation.IntDef;

import static com.keiferstone.nonet.ConnectionStatus.*;

@IntDef({CONNECTED, DISCONNECTED, UNKNOWN})
public @interface ConnectionStatus {
    int CONNECTED = 100;
    int DISCONNECTED = 101;
    int UNKNOWN = 102;
}
