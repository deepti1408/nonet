package com.keiferstone.nonet;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class BannerFactory {

    static BannerView getBanner(Context context) {
        return getBanner(context, R.string.no_server_connection_message);
    }

    static BannerView getBanner(Context context, String message) {
        ViewGroup root = (ViewGroup) extractView(context);
        BannerView banner = (BannerView) LayoutInflater.from(context)
                .inflate(R.layout.view_default_banner, root, false);
        banner.setText(message);
        if (root != null) {
            root.addView(banner);
        }
        return banner;
    }

    static BannerView getBanner(Context context, @StringRes int messageRes) {
        ViewGroup root = (ViewGroup) extractView(context);
        BannerView banner = (BannerView) LayoutInflater.from(context)
                .inflate(R.layout.view_default_banner, root, false);
        banner.setText(messageRes);
        if (root != null) {
            root.addView(banner);
        }
        return banner;
    }

    @Nullable
    private static View extractView(Context context) {
        if (context != null && context instanceof Activity) {
            return ((Activity) context).findViewById(android.R.id.content);
        }

        return null;
    }
}
