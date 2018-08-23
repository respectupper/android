package com.hhtxproject.piafriendscollege.NavFragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.hhtxproject.piafriendscollege.Entity.PiaUser;
import com.hhtxproject.piafriendscollege.NavFragment.Login.LoginActivity;
import com.hhtxproject.piafriendscollege.NavFragment.Login.service.CheckLogin;
import com.hhtxproject.piafriendscollege.NavFragment.GetMineData.GetMineData;
import com.hhtxproject.piafriendscollege.Net.LoadData;
import com.hhtxproject.piafriendscollege.OtherActivity.SettingActivity;
import com.hhtxproject.piafriendscollege.OtherActivity.UserinfoActivity;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Tools.CircleImageView;
import com.hhtxproject.piafriendscollege.app.PApplication;
import com.loopj.android.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.iv_mine_icon)
    CircleImageView ivMineIcon;
    @BindView(R.id.tv_mine_username)
    TextView tvMineUsername;
    @BindView(R.id.tv_mine_content)
    TextView tvMineContent;
    @BindView(R.id.nested_scrollview)
    NestedScrollView nestedScrollview;
    Unbinder unbinder;
    @BindView(R.id.tv_mine_funs)
    TextView tvMineFuns;
    @BindView(R.id.tv_mine_coin)
    TextView tvMineCoin;
    @BindView(R.id.tv_mine_follows)
    TextView tvMineFollows;
    @BindView(R.id.ll_blocktwo)
    LinearLayout llBlocktwo;

    private View view;
    private Toolbar toolbar;
    private TextView title;

    private PApplication pia;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    setViewData(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        pia = (PApplication) getActivity().getApplicationContext();

        if (pia.isLogin()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RequestParams params = new RequestParams();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                    if (new CheckLogin(sharedPreferences).cheak()){
                        params.add("telephone",sharedPreferences.getString("Telephone","NULL"));
                        params.add("password",sharedPreferences.getString("Password","NULL"));
                        new LoadData(handler,"/resquest/getData",params,200).getData().sendToTarget();
                    }
                }
            }).start();
        }
        Log.i("tag", "setHasOptionsMenu开启");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        init();
        initToolbar();
        unbinder = ButterKnife.bind(this, view);
        if (!pia.isLogin()) {
            llBlocktwo.setVisibility(View.GONE);
            tvMineUsername.setText("点击头像登录");
        }
        setClick();
        return view;
    }

    private void init() {
        toolbar = view.findViewById(R.id.mine_toolbar);
        title = toolbar.findViewById(R.id.toolbar_title);
    }

    private void initToolbar() {
        toolbar.setTitle("");
        title.setText("小窝");
        getActivity().setActionBar(toolbar);
    }

    private void setClick() {
        if (!pia.isLogin()){
            ivMineIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            ivMineIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), UserinfoActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("onActivityCreated", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (pia.isLogin()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RequestParams params = new RequestParams();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                    if (new CheckLogin(sharedPreferences).cheak()){
                        params.add("telephone",sharedPreferences.getString("Telephone","NULL"));
                        params.add("password",sharedPreferences.getString("Password","NULL"));
                        new LoadData(handler,"/resquest/getData",params,200).getData().sendToTarget();
                    }
                }
            }).start();
            llBlocktwo.setVisibility(View.VISIBLE);
            setClick();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("onStop", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroyView", "onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "onDestroy");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        Log.i("onHiddenChanged", "onHiddenChanged" );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
        inflater.inflate(R.menu.menu_mine, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_mine_bt1:
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setViewData(String msg){
        if ("FAILED".equals(msg)){
            Toast.makeText(getContext(),"登录失败",Toast.LENGTH_SHORT).show();
        } else{
            PiaUser user = new GetMineData(msg).getUser();
            tvMineUsername.setText(user.getUsername());
            tvMineContent.setText(user.getText());
            tvMineFuns.setText(user.getFuncount()+"");
            tvMineFollows.setText(user.getFollowcount()+"");
            tvMineCoin.setText(user.getCoin()+"");
            Glide.with(getContext())
                    .load(user.getAvatar())
                    .thumbnail(0.6f)
                    .into(ivMineIcon);
        }
    }
}
