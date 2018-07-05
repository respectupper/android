package com.hhtxproject.piafriendscollege.Net;

import android.os.Handler;
import android.os.Message;

import com.hhtxproject.piafriendscollege.Net.HttpPost.HttpPost;
import com.loopj.android.http.RequestParams;


/**
 * Created by Respectupper on 2018/6/5.
 */

public class LoadData {

    private Handler handler;
    private String servlet;
    private RequestParams params;
    public LoadData(Handler handler,String servlet,RequestParams params){
        this.handler = handler;
        this.servlet = servlet;
        this.params = params;
    }
    public Message getData(){
        Message msg = Message.obtain(handler);
        msg.obj = HttpPost.linkHttpPost(servlet,params);
        msg.what = 200;
        return msg;
    }
}
