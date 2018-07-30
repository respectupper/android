package com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.hhtxproject.piafriendscollege.Adapter.CSScriptAdapter;
import com.hhtxproject.piafriendscollege.Adapter.ChoiceScriptAdapter;
import com.hhtxproject.piafriendscollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChoiceScriptActivity extends AppCompatActivity {

    @BindView(R.id.mode_return)
    ImageButton modeReturn;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.choice_script_up)
    ImageView choiceScriptUp;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.choice_script_down)
    ImageView choiceScriptDown;
    @BindView(R.id.mRecyclerViewTwo)
    RecyclerView mRecyclerViewTwo;

    private ChoiceScriptAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_script);
        ButterKnife.bind(this);
        fullScreen(ChoiceScriptActivity.this);
        addAdapter();
        addScriptAdapter();

    }

    private void addScriptAdapter() {
        GridLayoutManager manager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewTwo.setLayoutManager(manager);
        CSScriptAdapter csScriptAdapter = new CSScriptAdapter();
        mRecyclerViewTwo.setAdapter(csScriptAdapter);
    }

    private void addAdapter() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        横向
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ChoiceScriptAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 通过设置全屏，设置状态栏透明
     * 沉浸式布局
     *
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }
}
