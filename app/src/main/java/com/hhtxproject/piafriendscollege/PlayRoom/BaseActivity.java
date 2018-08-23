package com.hhtxproject.piafriendscollege.PlayRoom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Adapter.ViewPagerAdapter;
import com.hhtxproject.piafriendscollege.Entity.PiaScript;
import com.hhtxproject.piafriendscollege.Net.LoadData;
import com.hhtxproject.piafriendscollege.PlayRoom.ShowContent.SelectFragment;
import com.hhtxproject.piafriendscollege.PlayRoom.ShowContent.ShowFragment;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Tools.ViewPagerSlide;
import com.hhtxproject.piafriendscollege.test.QueryTest;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPagerSlide viewpager;
    private List<Fragment> fragments;
    private ViewPagerAdapter adapter;
    public String data = "null";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ((int)msg.what==200&&!"".equals(msg.obj)){
                data = (String) msg.obj;
                setInitViewpager();
            }else {
                Toast.makeText(BaseActivity.this,"null",Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        getData();
    }

    private void setInitViewpager() {
        fragments = new ArrayList<>();
        fragments.add(SelectFragment.newInstance());
        fragments.add(ShowFragment.newInstance(data));
        FragmentManager manager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(manager, fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(1);
        viewpager.setSlide(false);
    }

    private void getData(){
        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(6));
        new Thread(new Runnable() {
            @Override
            public void run() {
                new LoadData(handler,"/request/showScript",params,200).getData().sendToTarget();
            }
        }).start();
    }
}
