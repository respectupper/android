package com.hhtxproject.piafriendscollege.Net.HttpPost;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

/**
 * Created by Respectupper on 2018/3/29.
 */

public class HttpPost {

    private static String responseMsg = "FAILED";

    // 通过 POST 方式获取HTTP服务器数据
    public static String linkHttpPost(String path,RequestParams params) {
        String baseURL = "http://192.168.0.119:8080"+path;//SERVER + PROJECT + servlet;
        SyncHttpClient client = new SyncHttpClient();
        client.post(baseURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                try {//解密
                    if (i==200){
                        responseMsg = new String(bytes);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {
                Log.i("code", "1 HttpPost: responseCode = " + i+"");
            }
        });
        return responseMsg;
    }
}
