package me.mayok.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;


/**
 * Created by mayok on 2016/10/22.
 */

public class HTTPUtils {

    private static Context mContext;
    private static final String DEBUG_TAG = "HttpExample";
    private TextView partnersFuman;

    public HTTPUtils(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
