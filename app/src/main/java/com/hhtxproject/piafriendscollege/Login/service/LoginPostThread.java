package com.hhtxproject.piafriendscollege.Login.service;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.app.PApplication;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

import okhttp3.FormBody;

/**
 * Created by Respectupper on 2018/4/24.
 */

public class LoginPostThread extends Thread{

    private String telephone;
    private String password;
    private Activity context;
    private PApplication app;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {  // 处理发送线程传回的消息
                if (msg.obj.toString().equals("NO_SUCH_USER")) {
                    Toast.makeText(context, "无用户", Toast.LENGTH_SHORT).show();
                } else if (msg.obj.toString().equals("FAILED")) {
                    Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
                } else{
                    app.setTelephone(telephone);
                    app.setLogin(true);
                    sharePreference(telephone,password);
                    context.finish();
                }
            }
        }
    };


    public LoginPostThread(Activity context,String telephone,String password){
        this.telephone = telephone;
        this.password = password;
        this.context = context;
        app = (PApplication) context.getApplicationContext();
    }

    @Override
    public void run() {
        try{
            RequestParams params = new RequestParams();
            params.add("pia_telephone",telephone);
            params.add("pia_password",password);//传递键值对参数

//            RequestParams params = new RequestParams();
//            params.add("pia_telephone",telephone);
//            params.add("pia_password",password);
//            params.add("requestKey",new PApplication().getRequestKEY());
            String tag = LoginService.send(params);
            Message msg = handler.obtainMessage();
            msg.what = 200;
            msg.obj = tag;
            handler.sendMessage(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sharePreference(String telephone,String password){
        //获取到edit对象
        SharedPreferences.Editor edit = app.getmSharedPreferences().edit();
        //通过editor对象写入数据
        edit.putString("Telephone",telephone);
        edit.putString("Password",password);
        //提交数据存入到xml文件中
        edit.commit();
    }

}
