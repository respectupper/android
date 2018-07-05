package com.hhtxproject.piafriendscollege.OtherActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhtxproject.piafriendscollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserinfoActivity extends AppCompatActivity {

    @BindView(R.id.right)
    ImageView right;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_telephone)
    TextView tvTelephone;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
    }
}
