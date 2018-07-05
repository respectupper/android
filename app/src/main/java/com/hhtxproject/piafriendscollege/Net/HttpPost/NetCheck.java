package com.hhtxproject.piafriendscollege.Net.HttpPost;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Respectupper on 2018/4/23.
 */

public class NetCheck {
    private Context mthis;
    public NetCheck(Context mthis){
        this.mthis = mthis;
    }
    public boolean isConnectionToInternet(){
        ConnectivityManager manager = (ConnectivityManager) mthis.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager !=null){
            NetworkInfo[] info = manager.getAllNetworkInfo();
            if (info != null){
                for (int i = 0;i<info.length;i++){
                    return true;
                }
            }
        }
        return false;
    }
}
