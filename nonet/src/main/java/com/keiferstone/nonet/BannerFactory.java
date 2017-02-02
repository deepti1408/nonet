package com.keiferstone.nonet;


import android.content.Context;
import android.support.annotation.StringRes;

class BannerFactory {

    static BannerView getBanner(Context context) {
        return getBanner(context, R.string.no_server_connection_message);
    }

    static BannerView getBanner(Context context, String message) {
        return new BannerView(context);
    }

    static BannerView getBanner(Context context, @StringRes int messageRes) {
        return new BannerView(context);
    }
}
