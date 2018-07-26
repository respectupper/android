package com.hhtxproject.piafriendscollege.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Login.service.LoginPostThread;
import com.hhtxproject.piafriendscollege.Net.HttpPost.NetCheck;
import com.hhtxproject.piafriendscollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_telephone)
    EditText etTelephone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        //检测是否登陆过
//        if (new CheckLogin(mSharedPreferences = getSharedPreferences("loginUser", Context.MODE_PRIVATE)).cheak()){
//            etTelephone.setText(mSharedPreferences.getString("pia_telephone","NULL"));
//            etPassword.setText(mSharedPreferences.getString("pia_password","NULL"));
//        }
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telephone = etTelephone.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (new NetCheck(LoginActivity.this).isConnectionToInternet()) {
                    if ("".equals(telephone) || "".equals(password)) {
                        Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                    } else {
                        //登录检测加跳转
                        new LoginPostThread(LoginActivity.this, telephone, password).start();
                    }
                }else {

                }
            }
        });
    }

}
