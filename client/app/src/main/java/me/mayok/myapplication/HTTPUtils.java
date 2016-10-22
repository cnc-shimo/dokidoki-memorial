package me.mayok.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import static me.mayok.myapplication.R.id.textView;

/**
 * Created by mayok on 2016/10/22.
 */

public class HTTPUtils {

    private static Context mContext;
    private static final String DEBUG_TAG = "HttpExample";

    public HTTPUtils(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
