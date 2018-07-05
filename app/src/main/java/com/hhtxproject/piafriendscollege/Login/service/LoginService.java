package com.hhtxproject.piafriendscollege.Login.service;

import com.hhtxproject.piafriendscollege.Net.HttpPost.HttpPost;
import com.loopj.android.http.RequestParams;

import java.io.IOException;

/**
 * Created by Respectupper on 2018/4/23.
 */

public class LoginService {
    public static String send(RequestParams params) throws IOException {
        String servlet = "login_pia";
        String responseMsg = "FAILED";
        responseMsg = HttpPost.linkHttpPost(servlet,params);
        return responseMsg;
    }
}
